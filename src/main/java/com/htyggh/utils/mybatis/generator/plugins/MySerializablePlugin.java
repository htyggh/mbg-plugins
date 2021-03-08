package com.htyggh.utils.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;
import java.util.Properties;

/**
 * <p>标题：MySerializablePlugin</p>
 * <p>描述：自定义MybatisGenerator序列化插件</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2018年09月12日15时09分</p>
 * <p>版本：</p>
 */
public class MySerializablePlugin extends PluginAdapter {
    private final FullyQualifiedJavaType serializable = new FullyQualifiedJavaType("java.io.Serializable");
    private final FullyQualifiedJavaType gwtSerializable = new FullyQualifiedJavaType("com.google.gwt.user.client.rpc.IsSerializable");
    private boolean addGWTInterface;
    private boolean suppressJavaInterface;

    public MySerializablePlugin() {
    }

    @Override
    public boolean validate(List<String> warnings) {
        return true;
    }

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        this.addGWTInterface = Boolean.parseBoolean(properties.getProperty("addGWTInterface"));
        this.suppressJavaInterface = Boolean.parseBoolean(properties.getProperty("suppressJavaInterface"));
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        this.makeSerializable(topLevelClass, introspectedTable);

        for (InnerClass innerClass : topLevelClass.getInnerClasses()) {
            if ("GeneratedCriteria".equals(innerClass.getType().getShortName())) {
                this.makeSerializableInner(innerClass, introspectedTable);
            }

            if ("Criteria".equals(innerClass.getType().getShortName())) {
                this.makeSerializableInner(innerClass, introspectedTable);
            }

            if ("Criterion".equals(innerClass.getType().getShortName())) {
                this.makeSerializableInner(innerClass, introspectedTable);
            }
        }

        return true;
    }

    private void makeSerializable(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (this.addGWTInterface) {
            topLevelClass.addImportedType(this.gwtSerializable);
            topLevelClass.addSuperInterface(this.gwtSerializable);
        }

        if (!this.suppressJavaInterface) {
            topLevelClass.addImportedType(this.serializable);
            topLevelClass.addSuperInterface(this.serializable);
            Field field = new Field("serialVersionUID", new FullyQualifiedJavaType("long"));
            field.setFinal(true);
            field.setInitializationString("1L");
            field.setStatic(true);
            field.setVisibility(JavaVisibility.PRIVATE);
            this.context.getCommentGenerator().addFieldComment(field, introspectedTable);
            topLevelClass.addField(field);
        }

    }

    private void makeSerializableInner(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (this.addGWTInterface) {
            innerClass.addSuperInterface(this.gwtSerializable);
        }

        if (!this.suppressJavaInterface) {
            innerClass.addSuperInterface(this.serializable);
            Field field = new Field("serialVersionUID", new FullyQualifiedJavaType("long"));
            field.setFinal(true);
            field.setInitializationString("1L");

            field.setStatic(true);
            field.setVisibility(JavaVisibility.PRIVATE);
            field.setTransient(false);
            this.context.getCommentGenerator().addFieldComment(field, introspectedTable);
            innerClass.addField(field);
        }

    }
}
