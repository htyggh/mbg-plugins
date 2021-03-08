package com.htyggh.utils.mybatis.generator.templates.comment.internal.velocity;

import com.htyggh.utils.mybatis.generator.templates.comment.CommentTemplateAdapter;
import com.htyggh.utils.mybatis.generator.templates.engine.TemplateContainer;
import com.htyggh.utils.mybatis.generator.templates.engine.TemplateEngine;
import com.htyggh.utils.mybatis.generator.templates.engine.internal.VelocityTemplateContainer;
import com.htyggh.utils.mybatis.generator.templates.engine.internal.VelocityTemplateEngine;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.util.Properties;

import static com.htyggh.utils.mybatis.generator.templates.TemplateType.getVelocity;

/**
 * <p>标题：VelocityCommentTemplateAdapter</p>
 * <p>描述：</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年07月04日11时44分</p>
 * <p>版本：</p>
 */
public abstract class VelocityCommentTemplateAdapter extends CommentTemplateAdapter<VelocityEngine> {

    private final String rootPath;

    private VelocityEngine velocityEngine;

    protected VelocityCommentTemplateAdapter() {
        super();
        rootPath = loadTemplateRootPath() == null ? "" : loadTemplateRootPath();
    }

    /**
     * 加载模版引擎
     *
     * @return 模版引擎
     */
    @Override
    protected TemplateEngine<VelocityEngine> loadTemplateEngine() {
        VelocityTemplateEngine velocityTemplateEngine = new VelocityTemplateEngine();
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/velocity.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        velocityEngine = getVelocity();
        velocityEngine.init(properties);
        velocityTemplateEngine.wrap(velocityEngine);
        return velocityTemplateEngine;
    }

    /**
     * 获取pojo类 字段注释模版
     *
     * @return pojo类 字段注释模版
     */
    @Override
    public TemplateContainer<Template> getFieldCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/ModelFieldCommentTemplate.vm"));
    }

    /**
     * 获取pojo类 类注释模版
     *
     * @return pojo类 类注释模版
     */
    @Override
    public TemplateContainer<Template> getModelClassCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/ModelClassCommentTemplate.vm"));
    }

    /**
     * 获取pojo类 getter方法注释模版
     *
     * @return pojo类 getter方法注释模版
     */
    @Override
    public TemplateContainer<Template> getModelGetMethodCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/ModelGetMethodCommentTemplate.vm"));
    }

    /**
     * 获取pojo类 getter方法注释模版
     *
     * @return pojo类 getter方法注释模版
     */
    @Override
    public TemplateContainer<Template> getModelSetMethodCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/ModelSetMethodCommentTemplate.vm"));
    }

    /**
     * 获取mapper类 方法注释模版
     *
     * @return mapper类 方法注释模版
     */
    @Override
    public TemplateContainer<Template> getMapperMethodCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/MapperMethodCommentTemplate.vm"));
    }

    /**
     * 获取mapper类 类注释模版
     *
     * @return mapper类 类注释模版
     */
    @Override
    public TemplateContainer<Template> getMapperClassCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/MapperClassCommentTemplate.vm"));
    }

    /**
     * 获取查询构建器 字段注释模版
     *
     * @return 查询构建器字段注释模版
     */
    @Override
    public TemplateContainer<Template> getExampleFieldCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/ExampleFieldCommentTemplate.vm"));
    }

    /**
     * 获取查询构建器 内部类注释模版
     *
     * @return 查询构建器内部类注释模版
     */
    @Override
    public TemplateContainer<Template> getExampleInnerClassCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/ExampleInnerClassCommentTemplate.vm"));
    }

    /**
     * 获取查询构建器 方法注释模版
     *
     * @return 查询构建器方法注释模版
     */
    @Override
    public TemplateContainer<Template> getExampleMethodCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/ExampleMethodCommentTemplate.vm"));
    }

    /**
     * 获取SQL生成器 类注释模版
     *
     * @return SQL生成器 类注释模版
     */
    @Override
    public TemplateContainer<Template> getSqlProviderClassCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/SqlProviderClassCommentTemplate.vm"));
    }

    /**
     * 获取DSL支持类 类注释模版
     *
     * @return DSL支持类 类注释模版
     */
    @Override
    public TemplateContainer<Template> getDSLSupportClassCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/DSLSupportClassCommentTemplate.vm"));
    }

    /**
     * 获取DSL支持类 内部类注释模版
     *
     * @return DSL支持类 内部类注释模版
     */
    @Override
    public TemplateContainer<Template> getDSLSupportInnerClassCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/DSLSupportInnerClassCommentTemplate.vm"));
    }

    /**
     * 获取DSL支持类 字段注释模版
     *
     * @return DSL支持类 字段注释模版
     */
    @Override
    public TemplateContainer<Template> getDSLSupportClassFieldCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/DSLSupportClassFieldCommentTemplate.vm"));
    }

    /**
     * 获取DSL支持类 内部类字段注释模版
     *
     * @return DSL支持类 内部类字段注释模版
     */
    @Override
    public TemplateContainer<Template> getDSLSupportInnerClassFieldCommentTemplate() {
        return new VelocityTemplateContainer(generatorTemplate("/DSLSupportInnerClassFieldCommentTemplate.vm"));
    }

    /**
     * 获取文件注释模版
     *
     * @return 文件注释模版
     */
    @Override
    public TemplateContainer<Template> getFileCommentTemPlate() {
        return new VelocityTemplateContainer(generatorTemplate("/FileComment.vm"));
    }

    /**
     * 生成注释模版
     *
     * @param fileName 模版文件名称
     * @return 模版对象
     */
    private Template generatorTemplate(String fileName) {
        return velocityEngine.getTemplate(rootPath + fileName, "UTF-8");
    }

    /**
     * 加载模版文件根目录
     *
     * @return 模版文件根目录
     */
    protected abstract String loadTemplateRootPath();

}
