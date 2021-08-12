package com.wuxp.codegen.mapping;

import com.wuxp.codegen.core.parser.LanguageTypeDefinitionParser;
import com.wuxp.codegen.model.CommonCodeGenClassMeta;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public abstract class AbstractMappingTypeDefinitionParserBuilder<C extends CommonCodeGenClassMeta> {

    /**
     * 类型映射器
     */
    private final Map<Class<?>, C> typeMappings;

    /**
     * 自定义的java类型映射
     */
    private final Map<Class<?>, Class<?>[]> javaTypeMappings;

    AbstractMappingTypeDefinitionParserBuilder(Map<Class<?>, C> baseMappings) {
        this.typeMappings = new HashMap<>(baseMappings);
        this.javaTypeMappings = new HashMap<>(32);
    }

    public AbstractMappingTypeDefinitionParserBuilder<C> typeMapping(Class<?> clazz, C meta) {
        this.typeMappings.put(clazz, meta);
        return this;
    }

    public AbstractMappingTypeDefinitionParserBuilder<C> typeMapping(Map<Class<?>, ? extends CommonCodeGenClassMeta> typeMappings) {
        this.typeMappings.putAll((Map<Class<?>, ? extends C>) typeMappings);
        return this;
    }

    public AbstractMappingTypeDefinitionParserBuilder<C> javaTypeMappings(Class<?> clazz, Class<?>... classes) {
        this.javaTypeMappings.put(clazz, classes);
        return this;
    }

    public AbstractMappingTypeDefinitionParserBuilder<C> javaTypeMappings(Map<Class<?>, Class<?>[]> javaTypeMappings) {
        this.javaTypeMappings.putAll(javaTypeMappings);
        return this;
    }

    public abstract LanguageTypeDefinitionParser<C> build();

}
