package quickcore.compiler.util;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class TypeUtil {
    private static ClassName list = ClassName.get("java.util", "List");
    private static ClassName arrayList = ClassName.get("java.util", "ArrayList");
    private static ClassName classClass = ClassName.get("java.lang", "Class");
    public static ClassName repositoryStore = ClassName.get("com.kbryant.quickcore.repository.impl", "RepositoryStore");
    public static TypeName listOfClass = ParameterizedTypeName.get(list, classClass);
    public static TypeName arrayListOfClass = ParameterizedTypeName.get(arrayList, classClass);
    public static ClassName repositoryStoreHelperClassName = ClassName.get("com.kbryant.quickcore.repository", "IRepositoryStoreHelper");
}
