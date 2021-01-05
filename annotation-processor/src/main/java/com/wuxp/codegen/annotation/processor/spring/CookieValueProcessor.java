package com.wuxp.codegen.annotation.processor.spring;

import com.wuxp.codegen.annotation.processor.AbstractAnnotationProcessor;
import com.wuxp.codegen.annotation.processor.AnnotationMate;
import com.wuxp.codegen.model.CommonCodeGenAnnotation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;

import java.lang.reflect.Parameter;
import java.text.MessageFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author wxup
 * @see CookieValue
 * 处理 CookieValue 注解
 */
public class CookieValueProcessor extends AbstractAnnotationProcessor<CookieValue, CookieValueProcessor.CookieValueMate> {


    @Override
    public CookieValueProcessor.CookieValueMate process(CookieValue annotation) {

        return super.newProxyMate(annotation, CookieValueProcessor.CookieValueMate.class);
    }


    public abstract static class CookieValueMate implements AnnotationMate, CookieValue {

        public CookieValueMate() {
        }

        @Override
        public CommonCodeGenAnnotation toAnnotation(Parameter annotationOwner) {
            CommonCodeGenAnnotation annotation = new CommonCodeGenAnnotation();
            annotation.setName(CookieValue.class.getSimpleName());
            Map<String, String> arguments = new LinkedHashMap<>();
            String value = this.value();
            if (!StringUtils.hasText(value)) {
                value = this.name();
            }
            if (!StringUtils.hasText(value)) {
                value = annotationOwner.getName();
            }
            if (StringUtils.hasText(value)) {
                arguments.put("name", MessageFormat.format("\"{0}\"", value));
            }
            arguments.put("required", this.required() + "");
            //注解位置参数
            List<String> positionArguments = new LinkedList<>(arguments.values());
            annotation.setNamedArguments(arguments)
                    .setPositionArguments(positionArguments);
            return annotation;
        }

        @Override
        public String toComment(Parameter annotationOwner) {

            return String.format("参数：%s是一个cookie value, %s，默认值为：%s", annotationOwner.getName(), required() ? "必填" : "非必填", defaultValue());
        }
    }
}
