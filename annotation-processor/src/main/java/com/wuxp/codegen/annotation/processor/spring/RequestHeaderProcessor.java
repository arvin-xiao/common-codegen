package com.wuxp.codegen.annotation.processor.spring;

import com.wuxp.codegen.annotation.processor.AbstractAnnotationProcessor;
import com.wuxp.codegen.annotation.processor.AnnotationMate;
import com.wuxp.codegen.model.CommonCodeGenAnnotation;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author wxup
 * @see RequestHeader
 * 处理 RequestHeader 注解
 */
public class RequestHeaderProcessor extends AbstractAnnotationProcessor<RequestHeader, RequestHeaderProcessor.RequestHeaderMate> {


    @Override
    public RequestHeaderProcessor.RequestHeaderMate process(RequestHeader annotation) {

        return super.newProxyMate(annotation, RequestHeaderProcessor.RequestHeaderMate.class);
    }


    public abstract static class RequestHeaderMate implements AnnotationMate<RequestHeader>, RequestHeader {

        public RequestHeaderMate() {
        }

        @Override
        public CommonCodeGenAnnotation toAnnotation(Field annotationOwner) {
            CommonCodeGenAnnotation annotation = new CommonCodeGenAnnotation();
            annotation.setName(RequestHeader.class.getName());
            Map<String, String> arguments = new LinkedHashMap<>();
            String value = this.value();
            if (!StringUtils.hasText(value)) {
                value = this.name();
            }
            arguments.put("name", value);
            //注解位置参数
            List<String> positionArguments = new LinkedList<>(arguments.values());
            annotation.setNamedArguments(arguments)
                    .setPositionArguments(positionArguments);
            return annotation;
        }

        @Override
        public String toComment(Field annotationOwner) {

            return MessageFormat.format("属性：{0}是一个 cookie", annotationOwner.getName());
        }
    }
}
