package com.htyggh.utils.mybatis.generator.plugins.dsl;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.ArrayList;
import java.util.List;

import static com.htyggh.utils.mybatis.generator.constant.BaseConst.MY_BATIS3_DYNAMIC_SQL;
import static com.htyggh.utils.mybatis.generator.constant.BaseConst.MY_BATIS3_DYNAMIC_SQL_V1;
import static com.htyggh.utils.mybatis.generator.constant.BaseConst.MY_BATIS3_DYNAMIC_SQL_V2;
import static com.htyggh.utils.mybatis.generator.utils.FiledNameConversion.toCamelCase;
import static com.htyggh.utils.mybatis.generator.utils.FiledNameConversion.toUpperSnakeCase;
import static org.apache.commons.lang3.StringUtils.replaceEach;

/**
 * 阿里巴巴p3c规范插件
 *
 * @author htyggh
 * @version 适用于MBG1.4
 * @date 2021年01月11日17时08分
 */
public class P3CDynamicSqlSupportPlugin extends DSLPluginAdapter {

    /**
     * 使{@code MyBatis3DynamicSql}模式的mapper符合阿里巴巴p3c规范
     *
     * @param interfaze         mapper接口
     * @param introspectedTable 表
     * @return 如果需要生成，则返回true;如果需要忽略生成，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        switch (targetRuntime) {
            case MY_BATIS3_DYNAMIC_SQL:
                myBatis3DynamicSqlV2(interfaze, introspectedTable);
                break;
            case MY_BATIS3_DYNAMIC_SQL_V2:
                myBatis3DynamicSqlV2(interfaze, introspectedTable);
                break;
            case MY_BATIS3_DYNAMIC_SQL_V1:
                myBatis3DynamicSqlV1(interfaze, introspectedTable);
                break;
            default:
                myBatis3DynamicSqlV2(interfaze, introspectedTable);
        }
        return true;
    }

    /**
     * 使{@code MyBatis3DynamicSqlV1}模式的mapper符合阿里巴巴p3c规范
     *
     * @param interfaze         mapper接口
     * @param introspectedTable 表
     */
    private void myBatis3DynamicSqlV1(Interface interfaze, IntrospectedTable introspectedTable) {

        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();
        List<String> oldFieldNames = new ArrayList<>(allColumns.size());
        List<String> newFieldNames = new ArrayList<>(allColumns.size());

        for (IntrospectedColumn column : allColumns) {
            String columnName = column.getActualColumnName();
            newFieldNames.add(columnName.toUpperCase());
            oldFieldNames.add(toCamelCase(columnName));
        }
        String oldFields = String.join(", ", oldFieldNames);
        String newFields = String.join(", ", newFieldNames);

        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();
        String oldDomainName = domainObjectName.substring(0, 1).toLowerCase() + domainObjectName.substring(1);
        String newDomainName = toUpperSnakeCase(oldDomainName);

        for (Method method : interfaze.getMethods()) {

            List<String> bodyLines = method.getBodyLines();

            formatParameters(method);

            for (int i = 0; i < bodyLines.size(); i++) {
                String newBodyLine = bodyLines.get(i);

                if (i == 0) {
                    newBodyLine = newBodyLine.replace(oldDomainName, newDomainName);
                    newBodyLine = newBodyLine.replace(oldFields, newFields);
                }

                if (newBodyLine.contains(".map") || newBodyLine.contains(".set") || newBodyLine.contains(".from") || newBodyLine.contains(".into")) {
                    newBodyLine = toUpperSnakeCaseByInBrackets(newBodyLine);
                }

                if (newBodyLine.contains(".where") || newBodyLine.contains(".and") || newBodyLine.contains(".or")) {
                    newBodyLine = toUpperSnakeCaseByWhere(newBodyLine);
                }

                bodyLines.set(i, newBodyLine);
            }
        }

    }

    /**
     * 使{@code MyBatis3DynamicSqlV1}模式的mapper符合阿里巴巴p3c规范
     *
     * @param interfaze         mapper接口
     * @param introspectedTable 表
     */
    private void myBatis3DynamicSqlV2(Interface interfaze, IntrospectedTable introspectedTable) {
        String[] oldFieldNames = new String[interfaze.getFields().size()];
        String[] newFieldNames = new String[interfaze.getFields().size()];
        List<Field> fields = interfaze.getFields();
        for (int i = 0; i < fields.size(); i++) {
            Field field = fields.get(i);
            String newFieldName = toUpperSnakeCase(field.getName());
            oldFieldNames[i] = field.getName();
            newFieldNames[i] = newFieldName;
            field.setName(newFieldName);
            String fieldValue = field.getInitializationString().orElse("");
            String substring = fieldValue.substring(fieldValue.indexOf("("), fieldValue.indexOf(")"));
            String upperSnakeCase = toUpperSnakeCase(substring);
            field.setInitializationString(fieldValue.replace(substring, upperSnakeCase));
        }

        String domainObjectName = introspectedTable.getTableConfiguration().getDomainObjectName();
        String oldDomainName = domainObjectName.substring(0, 1).toLowerCase() + domainObjectName.substring(1);
        String newDomainName = toUpperSnakeCase(oldDomainName);

        for (Method method : interfaze.getMethods()) {

            List<String> bodyLines = method.getBodyLines();

            formatParameters(method);

            for (int i = 0; i < bodyLines.size(); i++) {
                String newBodyLine = bodyLines.get(i);

                if (bodyLines.size() == 1) {
                    String oldLine = newBodyLine.substring(newBodyLine.indexOf("(") + 1, newBodyLine.indexOf(")"));
                    String newLine = replaceEach(oldLine, oldFieldNames, newFieldNames);
                    newBodyLine = newBodyLine.replace(oldLine, newLine);
                }

                if (i == 0) {
                    newBodyLine = newBodyLine.replace(oldDomainName, newDomainName);
                }

                if (newBodyLine.contains(".map") || newBodyLine.contains(".set")) {
                    newBodyLine = toUpperSnakeCaseByInBrackets(newBodyLine);
                }

                if (newBodyLine.contains(".where") || newBodyLine.contains(".and") || newBodyLine.contains(".or")) {
                    newBodyLine = toUpperSnakeCaseByWhere(newBodyLine);
                }

                bodyLines.set(i, newBodyLine);
            }
        }
    }

    /**
     * 将括号内的字段名转换为{@code SNAKE_CASE}格式
     *
     * @param bodyLine 字符串
     * @return 处理后的字符串
     */
    private String toUpperSnakeCaseByInBrackets(String bodyLine) {
        String filed = bodyLine.substring(bodyLine.indexOf("(") + 1, bodyLine.indexOf(")"));
        bodyLine = bodyLine.replaceFirst(filed, toUpperSnakeCase(filed));
        return bodyLine;
    }

    /**
     * 将查询条件内的字段名转换为{@code SNAKE_CASE}格式
     *
     * @param bodyLine 字符串
     * @return 处理后的字符串
     */
    private String toUpperSnakeCaseByWhere(String bodyLine) {
        String filed = bodyLine.substring(bodyLine.indexOf("(") + 1, bodyLine.indexOf(","));
        bodyLine = bodyLine.replaceFirst(filed, toUpperSnakeCase(filed));
        return bodyLine;
    }

    /**
     * 删除下划线
     *
     * @param bodyLines 方法体
     */
    private void removeUnderline(List<String> bodyLines) {
        for (int i = 0; i < bodyLines.size(); i++) {
            String bodyLine = bodyLines.get(i);
            bodyLines.set(i, bodyLine.replaceAll("_", ""));
        }
    }

    /**
     * 使参数符合阿里巴巴p3c规范
     *
     * @param method 方法对象
     */
    private void formatParameters(Method method) {
        List<Parameter> parameters = method.getParameters();
        for (int i = 0; i < parameters.size(); i++) {
            Parameter parameter = parameters.get(i);
            if (parameter.getName().contains("_")) {
                Parameter newParameter = new Parameter(parameter.getType(), parameter.getName().replaceAll("_", ""), parameter.isVarargs());
                for (String annotation : parameter.getAnnotations()) {
                    newParameter.addAnnotation(annotation);
                }
                parameters.set(i, newParameter);
                removeUnderline(method.getBodyLines());
            }
        }
    }

    /**
     * 使DSL支持类字段符合阿里巴巴p3c规范
     *
     * @param supportClass      DSL支持类
     * @param introspectedTable 表
     * @return 如果需要生成，则返回true;如果需要忽略生成，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean dynamicSqlSupportGenerated(TopLevelClass supportClass, IntrospectedTable introspectedTable) {
        List<Field> fields = supportClass.getFields();
        for (Field field : fields) {
            String newFieldName = toUpperSnakeCase(field.getName());
            field.setName(newFieldName);
            String fieldValue = field.getInitializationString().orElse("");
            if (fieldValue.contains(".")) {
                field.setInitializationString(toUpperSnakeCase(fieldValue));
            }
        }

        for (InnerClass innerClass : supportClass.getInnerClasses()) {
            for (Field field : innerClass.getFields()) {
                field.setName(toUpperSnakeCase(field.getName()));
            }
        }
        return true;
    }
}
