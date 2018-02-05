package quickcore.compiler;

import com.squareup.javapoet.ClassName;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

import quickcore.annotation.Repository;
import quickcore.compiler.out.RepositoryStoreHelper;

public class QuickCoreCompiler extends AbstractProcessor {
    private Elements elements;
    private Types types;
    private Filer filer;
    private Messager messager;
    private HashMap<String, ClassName> repositorys = new HashMap<String, ClassName>();

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
        elements = processingEnv.getElementUtils();
        types = processingEnv.getTypeUtils();
        filer = processingEnv.getFiler();
        messager = processingEnv.getMessager();
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new HashSet<String>();
        annotations.add(Repository.class.getCanonicalName());
        return annotations;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        findService(roundEnv);
        return true;
    }

    private void findService(RoundEnvironment roundEnv) {
        String packageName = "com.kbryant.quickcore.repository";
        for (Element element : roundEnv.getElementsAnnotatedWith(Repository.class)) {
            if (element.getKind().isInterface()) {
                PackageElement te = (PackageElement) element.getEnclosingElement();
                ClassName className = ClassName.get(te.getQualifiedName().toString(), element.getSimpleName().toString());
                repositorys.put(element.getSimpleName().toString(), className);
//                packageName = elements.getPackageOf(element).getQualifiedName().toString();
            }
        }
//        System.out.print("packageName=" + packageName);
        RepositoryStoreHelper.getInstance(repositorys).outJavaFile(filer, packageName);
    }
}
