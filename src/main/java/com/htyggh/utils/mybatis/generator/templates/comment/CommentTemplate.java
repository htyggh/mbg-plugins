package com.htyggh.utils.mybatis.generator.templates.comment;

import com.htyggh.utils.mybatis.generator.templates.engine.TemplateContainer;
import com.htyggh.utils.mybatis.generator.templates.engine.TemplateEngine;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

/**
 * <p>标题：CommentTemplate</p>
 * <p>描述：注释模版接口</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2018年09月17日11时11分</p>
 * <p>版本：1.0</p>
 */
public interface CommentTemplate {

    /**
     * 获取pojo类 字段注释模版
     *
     * @return pojo类 字段注释模版
     */
    TemplateContainer getFieldCommentTemplate();

    /**
     * 获取pojo类 类注释模版
     *
     * @return pojo类 类注释模版
     */
    TemplateContainer getModelClassCommentTemplate();

    /**
     * 获取pojo类 getter方法注释模版
     *
     * @return pojo类 getter方法注释模版
     */
    TemplateContainer getModelGetMethodCommentTemplate();

    /**
     * 获取pojo类 getter方法注释模版
     *
     * @return pojo类 getter方法注释模版
     */
    TemplateContainer getModelSetMethodCommentTemplate();

    /**
     * 获取mapper类 方法注释模版
     *
     * @return mapper类 方法注释模版
     */
    TemplateContainer getMapperMethodCommentTemplate();

    /**
     * 获取mapper类 类注释模版
     *
     * @return mapper类 类注释模版
     */
    TemplateContainer getMapperClassCommentTemplate();

    /**
     * 获取查询构建器 字段注释模版
     *
     * @return 查询构建器字段注释模版
     */
    TemplateContainer getExampleFieldCommentTemplate();

    /**
     * 获取查询构建器 内部类注释模版
     *
     * @return 查询构建器内部类注释模版
     */
    TemplateContainer getExampleInnerClassCommentTemplate();

    /**
     * 获取查询构建器 方法注释模版
     *
     * @return 查询构建器方法注释模版
     */
    TemplateContainer getExampleMethodCommentTemplate();

    /**
     * 获取SQL生成器 类注释模版
     *
     * @return SQL生成器 类注释模版
     */
    TemplateContainer getSqlProviderClassCommentTemplate();

    /**
     * 获取DSL支持类 类注释模版
     *
     * @return DSL支持类 类注释模版
     */
    TemplateContainer getDSLSupportClassCommentTemplate();

    /**
     * 获取DSL支持类 内部类注释模版
     *
     * @return DSL支持类 内部类注释模版
     */
    TemplateContainer getDSLSupportInnerClassCommentTemplate();

    /**
     * 获取DSL支持类 字段注释模版
     *
     * @return DSL支持类 字段注释模版
     */
    TemplateContainer getDSLSupportClassFieldCommentTemplate();

    /**
     * 获取DSL支持类 内部类字段注释模版
     *
     * @return DSL支持类 内部类字段注释模版
     */
    TemplateContainer getDSLSupportInnerClassFieldCommentTemplate();

    /**
     * 获取文件注释模版
     *
     * @return 文件注释模版
     */
    TemplateContainer getFileCommentTemPlate();

    /**
     * 获取模版引擎
     *
     * @return 模版引擎
     */
    TemplateEngine getTemplateEngine();
}