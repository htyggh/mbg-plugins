package com.htyggh.utils.mybatis.generator.templates.engine;

import java.io.IOException;

/**
 * <p>标题：TemplateEngine</p>
 * <p>描述：模版引擎包装类</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年07月03日11时50分</p>
 * <p>版本：</p>
 */
public interface TemplateEngine<T> {

    /**
     * 包装模版引擎
     *
     * @param templateEngine 模版引擎对象
     */
    void wrap(T templateEngine);

    /**
     * 获取模版引擎
     *
     * @return 模版引擎对象
     */
    T getTemplateEngine();

    /**
     * 获取模版容器
     *
     * @param name 模版名称
     * @return 模版容器对象
     */
    default TemplateContainer getTemplate(String name) throws IOException {
        return getTemplate(name, null);
    }

    /**
     * 获取模版容器
     *
     * @param name     模版名称
     * @param encoding 字符编码机
     * @return 模版容器对象
     */
    TemplateContainer getTemplate(String name, String encoding) throws IOException;
}
