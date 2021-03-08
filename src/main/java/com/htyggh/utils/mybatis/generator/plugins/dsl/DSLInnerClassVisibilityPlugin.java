package com.htyggh.utils.mybatis.generator.plugins.dsl;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Properties;

import static com.htyggh.utils.mybatis.generator.constant.BaseConst.MY_BATIS3_DYNAMIC_SQL;

import static com.htyggh.utils.mybatis.generator.registry.MyPropertyRegistry.DSL_INNERCLASS_VISIBILITY;
import static com.htyggh.utils.mybatis.generator.registry.MyPropertyRegistry.DSL_INNERCLASS_FIELD_VISIBILITY;
import static com.htyggh.utils.mybatis.generator.registry.MyPropertyRegistry.DSL_INNERCLASS_METHOD_VISIBILITY;

/**
 * <p>标题：DSLInnerClassVisibilityPlugin</p>
 * <p>描述：可以自由切换DSL支持类内部类访问控制符的插件</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年05月15日13时56分</p>
 * <p>版本：</p>
 */
public class DSLInnerClassVisibilityPlugin extends DSLPluginAdapter {

    /**
     * 内部类访问控制符
     */
    private JavaVisibility innerVisibility;

    /**
     * 内部类字段访问控制符
     */
    private JavaVisibility innerFieldVisibility;

    /**
     * 内部类方法访问控制符
     */
    private JavaVisibility innerMethodVisibility;

    /**
     * 加载配置
     *
     * @param properties 配置信息
     */
    @Override
    public void setProperties(Properties properties) {
        this.innerVisibility = JavaVisibility.valueOf(validate(properties.getProperty(DSL_INNERCLASS_VISIBILITY, "private").toUpperCase()));
        this.innerFieldVisibility = JavaVisibility.valueOf(validate(properties.getProperty(DSL_INNERCLASS_FIELD_VISIBILITY, "private").toUpperCase()));
        this.innerMethodVisibility = JavaVisibility.valueOf(validate(properties.getProperty(DSL_INNERCLASS_METHOD_VISIBILITY, "private").toUpperCase()));
    }

    /**
     * 验证访问控制符
     *
     * @param visibility 访问控制符
     * @return 有效的访问控制符
     */
    private String validate(String visibility) {
        if (!visibility.matches("PUBLIC|PRIVATE|PROTECTED|DEFAULT")) {
            throw new RuntimeException(visibility + "不是一个有效的访问控制符");
        }
        return visibility;
    }

    /**
     * 更改DSL支持类内部类的访问控制符
     * @param supportClass DSL支持类
     * @param introspectedTable 表
     * @return 如果需要生成，则返回true;如果需要忽略生成，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean dynamicSqlSupportGenerated(TopLevelClass supportClass, IntrospectedTable introspectedTable) {
        InnerClass innerClass = supportClass.getInnerClasses().get(0);
        innerClass.setVisibility(innerVisibility);
        for (Field field : innerClass.getFields()) {
            field.setVisibility(innerFieldVisibility);
        }
        for (Method method : innerClass.getMethods()) {
            method.setVisibility(innerMethodVisibility);
        }
        return true;
    }
}
