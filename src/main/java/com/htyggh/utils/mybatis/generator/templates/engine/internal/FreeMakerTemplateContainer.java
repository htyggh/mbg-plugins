package com.htyggh.utils.mybatis.generator.templates.engine.internal;

import com.htyggh.utils.mybatis.generator.templates.engine.TemplateContainer;
import freemarker.template.Template;

import java.io.Writer;
import java.util.Map;

/**
 * <p>标题：FreeMakerTemplateEngine</p>
 * <p>描述：FreeMaker模版容器</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年07月03日14时22分</p>
 * <p>版本：</p>
 */
public class FreeMakerTemplateContainer implements TemplateContainer<Template> {

    public FreeMakerTemplateContainer(Template template) {
        wrap(template);
    }

    private Template template;

    /**
     * 模版加工
     *
     * @param dataModel 数据实体
     * @param out       模版输出流
     */
    @Override
    public void process(Map<String, Object> dataModel, Writer out) throws Exception {
        template.process(dataModel, out);
    }

    /**
     * 包装模版对象
     *
     * @param template 模版对象
     */
    @Override
    public void wrap(Template template) {
        this.template = template;
    }

    /**
     * 获取模版对象
     *
     * @return 模版对象
     */
    @Override
    public Template getTemplate() {
        return template;
    }
}
