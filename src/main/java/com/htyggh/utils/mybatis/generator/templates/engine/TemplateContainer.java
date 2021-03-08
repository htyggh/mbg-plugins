package com.htyggh.utils.mybatis.generator.templates.engine;

import java.io.Writer;
import java.util.Map;

/**
 * <p>标题：TemplateContainer</p>
 * <p>描述：模版容器类</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年07月03日14时07分</p>
 * <p>版本：</p>
 */
public interface TemplateContainer<T> {

    /**
     * 模版加工
     *
     * @param dataModel 数据实体
     * @param out       模版输出流
     */
    void process(Map<String, Object> dataModel, Writer out) throws Exception;

    /**
     * 包装模版对象
     *
     * @param template 模版对象
     */
    void wrap(T template);

    /**
     * 获取模版对象
     *
     * @return 模版对象
     */
    T getTemplate();
}
