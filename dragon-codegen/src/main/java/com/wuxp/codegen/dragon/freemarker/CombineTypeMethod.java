package com.wuxp.codegen.dragon.freemarker;

import com.wuxp.codegen.core.strategy.CombineTypeDescStrategy;
import com.wuxp.codegen.dragon.strategy.SimpleCombineTypeDescStrategy;
import com.wuxp.codegen.model.CommonCodeGenClassMeta;
import freemarker.template.*;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class CombineTypeMethod implements TemplateMethodModelEx {

    /**
     * 泛型合并策略
     */
    protected CombineTypeDescStrategy combineTypeDescStrategy = new SimpleCombineTypeDescStrategy();

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        if (arguments.size() == 0) {
            throw new RuntimeException("arguments size is 0");
        }
        DefaultArrayAdapter arrayAdapter = (DefaultArrayAdapter) arguments.get(0);

        CommonCodeGenClassMeta[] commonCodeGenClassMetas = (CommonCodeGenClassMeta[]) arrayAdapter.getAdaptedObject(CommonCodeGenClassMeta.class);


        log.debug("要合并的泛型描述，length={}",commonCodeGenClassMetas.length);

        return this.combineTypeDescStrategy.combine(commonCodeGenClassMetas);
    }
}
