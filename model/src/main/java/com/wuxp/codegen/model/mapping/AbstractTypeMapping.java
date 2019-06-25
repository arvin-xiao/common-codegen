package com.wuxp.codegen.model.mapping;

import com.wuxp.codegen.model.CommonCodeGenClassMeta;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractTypeMapping<T> implements TypeMapping<Class<?>, List<T>> {

    /**
     * java to other language时基础数据类型的映射
     */
    public static final Map<Class<?>, CommonCodeGenClassMeta> BASE_TYPE_MAPPING = new LinkedHashMap<>();

    public static final Map<Class<?>, CommonCodeGenClassMeta> CUSTOMIZE_TYPE_MAPPING = new LinkedHashMap<>();

    /**
     * 自定义的类型映射
     * 将某个java类型映射成其他多个java 类型
     */
    public static final Map<Class<?>, Class<?>[]> CUSTOMIZE_JAVA_TYPE_MAPPING = new LinkedHashMap<>();


    /**
     * 基础类型映射器
     */
    public static final TypeMapping<Class<?>, ? extends CommonCodeGenClassMeta> baseTypeMapping = new BaseTypeMapping<>(BASE_TYPE_MAPPING);

    /**
     * 自定义的类型映射器
     */
    public static final TypeMapping<Class<?>, ? extends CommonCodeGenClassMeta> customizeTypeMapping = new BaseTypeMapping<>(CUSTOMIZE_TYPE_MAPPING);

    /**
     * 自定义的java类型映射
     */
    public static final TypeMapping<Class<?>, List<Class<?>>> customizeJavaTypeMapping = new CustomizeJavaTypeMapping(CUSTOMIZE_JAVA_TYPE_MAPPING);


}
