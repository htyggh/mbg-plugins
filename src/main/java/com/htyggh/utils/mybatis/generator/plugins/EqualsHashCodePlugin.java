package com.htyggh.utils.mybatis.generator.plugins;

import com.htyggh.utils.mybatis.generator.generate.equalshashcode.GeneratorEqualsHashCode;
import com.htyggh.utils.mybatis.generator.generate.equalshashcode.internal.JavaObjectsEqualsHashCode;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;
import java.util.Properties;

import static com.htyggh.utils.mybatis.generator.generate.GenerateFactory.getGeneratorEqualsHashCode;
import static com.htyggh.utils.mybatis.generator.registry.MyPropertyRegistry.COMMENT_TYPE;
import static com.htyggh.utils.mybatis.generator.registry.MyPropertyRegistry.EQUALS_HASHCODE_TYPE;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static com.htyggh.utils.mybatis.generator.utils.EqualsHashCodeUtils.init;
import static com.htyggh.utils.mybatis.generator.utils.EqualsHashCodeUtils.filter;

/**
 * <p>标题：EqualsHashCodePlugin</p>
 * <p>描述：EqualsHashCode插件</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年05月17日11时22分</p>
 * <p>版本：</p>
 */
public class EqualsHashCodePlugin extends PluginAdapter {

    private GeneratorEqualsHashCode generatorEqualsHashCode;

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
     *
     * @param properties 配置信息
     */
    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);

        String type = properties.getProperty(COMMENT_TYPE);

        if (!stringHasValue(type)) {
            type = JavaObjectsEqualsHashCode.class.getName();
        }

        generatorEqualsHashCode = getGeneratorEqualsHashCode(type);
        init(properties);
    }

    /**
     * 添加基本字段类EqualsHashCode方法
     *
     * @param topLevelClass     基本字段实体类
     * @param introspectedTable 表
     * @return 如果需要生成实体，则返回true;如果需要忽略生成的实体，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {

        String equalsHashCodeType = introspectedTable.getTableConfigurationProperty(EQUALS_HASHCODE_TYPE);

        List<IntrospectedColumn> columns = null;

        boolean key = introspectedTable.getRules().generatePrimaryKeyClass();

        boolean blob = introspectedTable.getRules().generateRecordWithBLOBsClass();

        if (!key && !blob) {
            columns = introspectedTable.getAllColumns();
        }

        if (key && !blob) {
            columns = introspectedTable.getNonPrimaryKeyColumns();
        }

        if (!key && blob) {
            columns = introspectedTable.getNonBLOBColumns();
        }

        if (key && blob) {
            columns = introspectedTable.getBaseColumns();
        }

        columns = filter(columns, introspectedTable.getTableConfiguration().getProperties());

        Method equals;
        Method hashCode;
        if (!stringHasValue(equalsHashCodeType)) {
            equals = generatorEqualsHashCode.generateEquals(topLevelClass, columns, introspectedTable);
            hashCode = generatorEqualsHashCode.generateHashCode(topLevelClass, columns, introspectedTable);
        } else {
            GeneratorEqualsHashCode equalsHashCode = getGeneratorEqualsHashCode(equalsHashCodeType);
            equals = equalsHashCode.generateEquals(topLevelClass, columns, introspectedTable);
            hashCode = equalsHashCode.generateHashCode(topLevelClass, columns, introspectedTable);
        }

        addMethod(topLevelClass, equals, hashCode);

        return true;
    }

    /**
     * 添加主键列实体类EqualsHashCode方法
     *
     * @param topLevelClass     主键实体类
     * @param introspectedTable 表
     * @return 如果需要生成实体，则返回true;如果需要忽略生成的实体，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean modelPrimaryKeyClassGenerated(TopLevelClass topLevelClass,
                                                 IntrospectedTable introspectedTable) {

        String equalsHashCodeType = introspectedTable.getTableConfigurationProperty(EQUALS_HASHCODE_TYPE);

        Method equals;
        Method hashCode;

        if (!stringHasValue(equalsHashCodeType)) {
            equals = generatorEqualsHashCode.generateEquals(topLevelClass, introspectedTable.getPrimaryKeyColumns(),
                    introspectedTable);
            hashCode = generatorEqualsHashCode.generateHashCode(topLevelClass, introspectedTable
                    .getPrimaryKeyColumns(), introspectedTable);
        } else {
            GeneratorEqualsHashCode equalsHashCode = getGeneratorEqualsHashCode(equalsHashCodeType);
            equals = equalsHashCode.generateEquals(topLevelClass, introspectedTable.getPrimaryKeyColumns(),
                    introspectedTable);
            hashCode = equalsHashCode.generateHashCode(topLevelClass, introspectedTable
                    .getPrimaryKeyColumns(), introspectedTable);
        }

        addMethod(topLevelClass, equals, hashCode);
        return true;
    }

    /**
     * 添加BLOB列实体类EqualsHashCode方法
     *
     * @param topLevelClass     BLOB列实体类
     * @param introspectedTable 表
     * @return 如果需要生成实体，则返回true;如果需要忽略生成的实体，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean modelRecordWithBLOBsClassGenerated(
            TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        String equalsHashCodeType = introspectedTable.getTableConfigurationProperty(EQUALS_HASHCODE_TYPE);

        Method equals;
        Method hashCode;
        if (!stringHasValue(equalsHashCodeType)) {
            equals = generatorEqualsHashCode.generateEquals(topLevelClass, introspectedTable.getAllColumns(),
                    introspectedTable);
            hashCode = generatorEqualsHashCode.generateHashCode(topLevelClass, introspectedTable.getAllColumns(),
                    introspectedTable);
        } else {
            GeneratorEqualsHashCode equalsHashCode = getGeneratorEqualsHashCode(equalsHashCodeType);
            equals = equalsHashCode.generateEquals(topLevelClass, introspectedTable.getBLOBColumns(),
                    introspectedTable);
            hashCode = equalsHashCode.generateHashCode(topLevelClass, introspectedTable.getBLOBColumns(),
                    introspectedTable);
        }

        addMethod(topLevelClass, equals, hashCode);
        return true;
    }

    /**
     * 为实体类添加方法
     *
     * @param topLevelClass 实体类
     * @param methods       方法集合
     */
    private void addMethod(TopLevelClass topLevelClass, Method... methods) {
        for (Method method : methods) {
            if (method == null) {
                break;
            }
            topLevelClass.addMethod(method);
        }
    }

}
