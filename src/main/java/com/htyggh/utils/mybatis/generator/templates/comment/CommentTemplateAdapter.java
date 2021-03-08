package com.htyggh.utils.mybatis.generator.templates.comment;

import com.htyggh.utils.mybatis.generator.templates.engine.TemplateEngine;

/**
 * <p>标题：CommentTemplateAdapter1111</p>
 * <p>描述：</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年07月04日11时27分</p>
 * <p>版本：</p>
 */
public abstract class CommentTemplateAdapter<T> implements CommentTemplate {

    /**
     * 模版引擎
     */
    private final TemplateEngine<T> templateEngine;

    protected CommentTemplateAdapter() {
        templateEngine = loadTemplateEngine();
    }

    /**
     * 加载模版引擎
     *
     * @return 模版引擎
     */
    protected abstract TemplateEngine<T> loadTemplateEngine();

    /**
     * 获取模版引擎
     *
     * @return 模版引擎
     */
    @Override
    public TemplateEngine getTemplateEngine() {
        return templateEngine;
    }
}
