package com.htyggh.utils.mybatis.generator.templates.engine.internal;

import com.htyggh.utils.mybatis.generator.templates.engine.TemplateContainer;
import com.htyggh.utils.mybatis.generator.templates.engine.TemplateEngine;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.IOException;

/**
 * <p>标题：FreeMakerTemplateEngine</p>
 * <p>描述：FreeMaker模版引擎</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年07月03日14时22分</p>
 * <p>版本：</p>
 */
public class FreeMakerTemplateEngine implements TemplateEngine<Configuration> {

    private Configuration configuration;

    /**
     * 包装模版引擎
     *
     * @param templateEngine 模版引擎对象
     */
    @Override
    public void wrap(Configuration templateEngine) {
        configuration = templateEngine;
    }

    /**
     * 获取模版引擎
     *
     * @return 模版引擎对象
     */
    @Override
    public Configuration getTemplateEngine() {
        return configuration;
    }

    /**
     * 获取模版容器
     *
     * @param name     模版名称
     * @param encoding 字符编码集
     * @return 模版容器对象
     */
    @Override
    public TemplateContainer<Template> getTemplate(String name, String encoding) throws IOException {
        return new FreeMakerTemplateContainer(configuration.getTemplate(name, encoding));
    }
}
