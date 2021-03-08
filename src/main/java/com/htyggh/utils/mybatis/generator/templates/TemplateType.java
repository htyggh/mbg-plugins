package com.htyggh.utils.mybatis.generator.templates;

import freemarker.template.Configuration;
import org.apache.velocity.app.VelocityEngine;

/**
 * <p>标题：TemplateType</p>
 * <p>描述：模版引擎单例类</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年07月08日16时01分</p>
 * <p>版本：</p>
 */
public class TemplateType {

    private TemplateType() {
    }

    /**
     * Velocity模版引擎
     */
    private static class Velocity {
        private static final VelocityEngine VELOCITY_ENGINE = new VelocityEngine();
    }

    /**
     * FreeMaker模版引擎
     */
    private static class FreeMaker {
        private static final Configuration CONFIGURATION = new Configuration(Configuration.VERSION_2_3_28);
    }

    /**
     * 获取Velocity模版引擎
     * @return Velocity模版引擎
     */
    public static VelocityEngine getVelocity() {
        return Velocity.VELOCITY_ENGINE;
    }

    /**
     * 获取FreeMaker模版引擎
     * @return FreeMaker模版引擎
     */
    public static Configuration getFreeMaker() {
        return FreeMaker.CONFIGURATION;
    }

}
