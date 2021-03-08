package com.htyggh.utils.mybatis.generator.templates.comment.internal.velocity;

/**
 * <p>标题：DefaultCommentTemplate</p>
 * <p>描述：简单注释模版</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2018年09月17日11时03分</p>
 * <p>版本：</p>
 */
public class SimpleCommentTemplate extends VelocityCommentTemplateAdapter {

    public SimpleCommentTemplate() {
        super();
    }

    /**
     * 加载模版文件根目录
     *
     * @return 模版文件根目录
     */
    @Override
    protected String loadTemplateRootPath() {
        return "/templates/comment/simple";
    }

}
