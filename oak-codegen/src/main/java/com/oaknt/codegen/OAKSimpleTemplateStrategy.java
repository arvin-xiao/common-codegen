package com.oaknt.codegen;

import com.oaknt.codegen.freemarker.CombineTypeMethod;
import com.oaknt.codegen.strategy.SimpleCombineTypeDescStrategy;
import com.oaknt.codegen.utils.FileUtil;
import com.wuxp.codegen.core.strategy.CombineTypeDescStrategy;
import com.wuxp.codegen.core.strategy.TemplateStrategy;
import com.wuxp.codegen.model.CommonCodeGenClassMeta;
import com.wuxp.codegen.model.CommonCodeGenMethodMeta;
import com.wuxp.codegen.model.enums.ClassType;
import com.wuxp.codegen.templates.TemplateLoader;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;


/**
 * 模板处理策略
 * <p>
 * 负责将数据和模板合并，生成最终的文件
 * </p>
 */
@Slf4j
public class OAKSimpleTemplateStrategy implements TemplateStrategy<CommonCodeGenClassMeta> {

    //在LAST_MODIFIED_MINUTE内生成的文件不在生成
    public static final float LAST_MODIFIED_MINUTE = 0.1f;




    /**
     * 模板加载器
     */
    protected TemplateLoader<Template> templateLoader;

    /**
     * 输出的根目录
     */
    protected String outputPath;

    /**
     * 文件扩展名称
     */
    protected String extName;

    public OAKSimpleTemplateStrategy(TemplateLoader<Template> templateLoader, String outputPath, String extName) {
        this.templateLoader = templateLoader;
        this.outputPath = outputPath.endsWith("\\") ? outputPath : outputPath + "\\";
        this.extName = extName;
    }

    @Override
    public void build(CommonCodeGenClassMeta data) {

        //根据是否为接口类型的元数据还是dto的类型的元数据加载不同的模板
        String templateName = null;
        CommonCodeGenMethodMeta[] methodMetas = data.getMethodMetas();
        if (methodMetas == null || methodMetas.length == 0) {
            //DTO or enum
            if (ClassType.ENUM.equals(data.getClassType())) {
                templateName = TemplateStrategy.API_ENUM_TEMPLATE_NAME;
            } else {
                //区分请求对象还是响应对象
                templateName = TemplateStrategy.API_REQUEST_TEMPLATE_NAME;
            }
        } else {
            //api 接口
            templateName = TemplateStrategy.API_SERVICE_TEMPLATE_NAME;

        }


        Template template = this.templateLoader.load(templateName);
        if (template == null) {
            log.warn("没有找到模板{}", templateName);
            return;
        }

        String output = Paths.get(this.outputPath + data.getPackagePath() + "." + this.extName).toString();
        File file = new File(output);
        if (file.exists()) {
            if (System.currentTimeMillis() - file.lastModified() <= LAST_MODIFIED_MINUTE * 60 * 1000) {
                log.warn("文件{}在{}分钟内已经生成过，跳过生成", output, LAST_MODIFIED_MINUTE);
                return;
            }
        }
        FileUtil.createDirectory(output.substring(0, output.lastIndexOf("\\")));
        log.info("生成类{}的文件，输出到{}目录", data.getName(), output);
        try {
            //输出
            Writer writer = new OutputStreamWriter(new FileOutputStream(output),
                    StandardCharsets.UTF_8);
            //添加自定义方法
            template.setCustomAttribute("combineType",new CombineTypeMethod());
            template.process(data, writer);
        } catch (TemplateException | IOException e) {
            e.printStackTrace();
        }
    }
}
