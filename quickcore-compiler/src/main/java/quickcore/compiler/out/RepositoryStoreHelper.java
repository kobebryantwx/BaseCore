package quickcore.compiler.out;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.HashMap;

import javax.annotation.processing.Filer;
import javax.lang.model.element.Modifier;

import quickcore.compiler.util.TypeUtil;

public class RepositoryStoreHelper {
    private HashMap<String, ClassName> hashMap;
    private static RepositoryStoreHelper instance;

    private RepositoryStoreHelper(HashMap<String, ClassName> hashMap) {
        this.hashMap = hashMap;
    }

    public static RepositoryStoreHelper getInstance(HashMap<String, ClassName> hashMap) {
        if (instance == null) {
            instance = new RepositoryStoreHelper(hashMap);
        }
        return instance;
    }

    private MethodSpec.Builder getMethodSpec() {
        MethodSpec.Builder builder = MethodSpec.methodBuilder("setRepository");
        builder.addAnnotation(Override.class);
        builder.addParameter(TypeUtil.repositoryStore, "repository");
        builder.addModifiers(Modifier.PUBLIC);
        builder.addStatement("$T services = new $T()", TypeUtil.listOfClass, TypeUtil.arrayListOfClass);
        for (String key : hashMap.keySet()) {
            builder.addStatement("services.add($T.class)", hashMap.get(key));
        }
        builder.addStatement("repository.addRetrofitService(services)");
        return builder;
    }

    private TypeSpec.Builder getTypeSpec() {
        TypeSpec.Builder tb = TypeSpec.classBuilder("AutoInjectRepository");
        tb.addModifiers(Modifier.PUBLIC);
        tb.addSuperinterface(TypeUtil.repositoryStoreHelperClassName);
        tb.addMethod(getMethodSpec().build());
        return tb;
    }

    public void outJavaFile(Filer filer, String packageName) {
        JavaFile.Builder javaFileBuilder = JavaFile.builder(packageName, getTypeSpec().build());
        try {
            javaFileBuilder.build().writeTo(filer);
        } catch (IOException e) {
            // Todo
        }
    }
}
