package com.htyggh.utils.mybatis.generator.templates.engine.internal;

import com.htyggh.utils.mybatis.generator.templates.engine.TemplateContainer;
import com.htyggh.utils.mybatis.generator.templates.engine.TemplateEngine;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

/**
 * <p>标题：VelocityTemplateEngine</p>
 * <p>描述：Velocity模版引擎</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年07月03日14时19分</p>
 * <p>版本：</p>
 */
public class VelocityTemplateEngine implements TemplateEngine<VelocityEngine> {

    /**
     * Velocity模版引擎
     */
    private VelocityEngine velocityEngine;

    /**
     * 包装模版引擎
     *
     * @param templateEngine 模版引擎对象
     */
    @Override
    public void wrap(VelocityEngine templateEngine) {
        velocityEngine = templateEngine;
    }

    /**
     * 获取模版引擎
     *
     * @return 模版引擎对象
     */
    @Override
    public VelocityEngine getTemplateEngine() {
        return velocityEngine;
    }

    /**
     * 获取模版容器
     *
     * @param name     模版名称
     * @param encoding 字符编码机
     * @return 模版容器对象
     */
    @Override
    public TemplateContainer<Template> getTemplate(String name, String encoding) {
        return new VelocityTemplateContainer(velocityEngine.getTemplate(name, encoding));
    }
}
