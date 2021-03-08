package com.htyggh.utils.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Properties;

/**
 * <p>标题：LombokPlugin</p>
 * <p>描述：Lombok支持插件</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年05月17日11时04分</p>
 * <p>版本：</p>
 */
public class LombokPlugin extends PluginAdapter {

    /**
     * 这个插件总是有效的
     *
     * @param warnings 插件无效的原因
     * @return 如果插件有效返回true 无效则返回 false
     */
    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * 加载配置
     * @param properties 配置信息
     */
    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
    }

    /**
     * 添加Lombok支持
     *
     * @param topLevelClass     类
     * @param introspectedTable 表
     */
    private void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

    }

    /**
     * 使用了Lombok就不再需要get方法这里屏蔽get方法的生成
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
        return false;
    }

    /**
     * 使用了Lombok就不再需要set方法这里屏蔽set方法的生成
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
        return false;
    }

}
