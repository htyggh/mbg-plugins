package com.htyggh.utils.mybatis.generator.plugins.dsl;

import com.htyggh.utils.mybatis.generator.generate.comment.GeneratorComment;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;
import java.util.Properties;

import static com.htyggh.utils.mybatis.generator.generate.GenerateFactory.getGeneratorComment;

import static com.htyggh.utils.mybatis.generator.constant.BaseConst.MY_BATIS3_DYNAMIC_SQL;


/**
 * <p>标题：DSLCommentGeneratorPlugin</p>
 * <p>描述：DSL 注释插件 由于 MBG1.3.7 未提供MyBatis3DynamicSQL模式的注释支持,所以这里使用插件来代替 这个问题我已在GITHUB上提交<a href="https://github.com/mybatis/generator/issues/463">点击此处查看详情。</a>在后续版本中此问题应该会被解决。</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年05月14日11时07分</p>
 * <p>版本：适用于MBG1.3.7及以上版本</p>
 */
public class DSLCommentGeneratorPlugin extends DSLPluginAdapter {

    /**
     * 注释模版工厂
     */
    private GeneratorComment commentTemplate;

    public DSLCommentGeneratorPlugin() {
        super();
    }

    /**
     * 加载配置
     *
     * @param properties 配置信息
     */
    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);

        commentTemplate = getGeneratorComment("com.htyggh.utils.mybatis.generator.generate.comment.internal.GeneratorCommentByTemplates");
        try {
            commentTemplate.loadConfigurationProperties(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加DSL支持类注释
     * @param supportClass DSL支持类
     * @param introspectedTable 表
     * @return DSL支持类，则返回true;如果需要忽略生成DSL支持类，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean dynamicSqlSupportGenerated(TopLevelClass supportClass, IntrospectedTable introspectedTable) {
        commentTemplate.addDynamicSqlSupportClassComment(supportClass, introspectedTable);
        return true;
    }

    /**
     * 添加Mapper接口注释
     *
     * @param interfaze         接口
     * @param introspectedTable 表
     * @return 如果需要生成，则返回true;如果需要忽略生成，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        commentTemplate.addMapperClassComment(interfaze, introspectedTable);
        return true;
    }



    /**
     * 添加实体类字段注释
     *
     * @param field              字段
     * @param topLevelClass      实体类
     * @param introspectedColumn 列对象
     * @param introspectedTable  表对象
     * @param modelClassType     实体类类型
     * @return 如果需要生成字段，则返回true;如果需要忽略生成的字段，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean modelFieldGenerated(Field field, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        commentTemplate.addFieldComment(field, introspectedTable, introspectedColumn);
        return true;
    }

    /**
     * 添加主键类型实体类类注释
     *
     * @param topLevelClass     实体类
     * @param introspectedTable 表
     * @return 如果需要生成字段，则返回true;如果需要忽略生成的字段，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addModelClassComment(topLevelClass, introspectedTable);
        return true;
    }

    /**
     * 添加基本类型实体类类注释
     *
     * @param topLevelClass     实体类
     * @param introspectedTable 表
     * @return 如果需要生成字段，则返回true;如果需要忽略生成的字段，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addModelClassComment(topLevelClass, introspectedTable);
        return true;
    }

    /**
     * 添加BLOB类型实体类类注释
     *
     * @param topLevelClass     实体类
     * @param introspectedTable 表
     * @return 如果需要生成字段，则返回true;如果需要忽略生成的字段，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean modelRecordWithBLOBsClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addModelClassComment(topLevelClass, introspectedTable);
        return true;
    }

    /**
     * 添加get方法注释
     *
     * @param method             方法
     * @param topLevelClass      实体类
     * @param introspectedColumn 列
     * @param introspectedTable  表
     * @param modelClassType     实体类类型
     * @return 如果需要生成字段，则返回true;如果需要忽略生成的字段，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        commentTemplate.addGetterComment(method, introspectedTable, introspectedColumn);
        return true;
    }

    /**
     * 添加set方法注释
     *
     * @param method             方法
     * @param topLevelClass      实体类
     * @param introspectedColumn 列
     * @param introspectedTable  表
     * @param modelClassType     实体类类型
     * @return 如果需要生成字段，则返回true;如果需要忽略生成的字段，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        commentTemplate.addSetterComment(method, introspectedTable, introspectedColumn);
        return true;
    }

    /**
     * 添加pojo类，类注释
     *
     * @param topLevelClass     类
     * @param introspectedTable 表
     */
    private void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        commentTemplate.addModelClassComment(topLevelClass, introspectedTable);
    }

}
