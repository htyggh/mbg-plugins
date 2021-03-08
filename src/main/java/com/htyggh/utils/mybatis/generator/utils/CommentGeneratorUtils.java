package com.htyggh.utils.mybatis.generator.utils;

import com.htyggh.utils.mybatis.generator.templates.engine.TemplateContainer;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.Method;

import java.io.IOException;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.htyggh.utils.mybatis.generator.constant.BaseConst.ENTER_REGEX;

/**
 * <p>标题：CommentGeneratorUtils</p>
 * <p>描述：注释插件工具类</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年05月14日11时44分</p>
 * <p>版本：</p>
 */
public class CommentGeneratorUtils {

    /**
     * 工具类不需要实例化
     */
    private CommentGeneratorUtils() {
    }

    /**
     * 获取当前时间字符串
     *
     * @return 当前时间字符串
     */
    public static String getDateString(SimpleDateFormat dateFormat) {
        return dateFormat.format(System.currentTimeMillis());
    }

    /**
     * 以换行符分割注释内容
     *
     * @param context 模版数据
     * @param template        模版
     * @return 分割后的注释数组
     */
    public static String[] splitComment(Map<String, Object> context, TemplateContainer<?> template) {
        StringWriter comment = new StringWriter();
        try {
            template.process(context, comment);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String comments = comment.toString();
        try {
            comment.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return comments.split(ENTER_REGEX);
    }

    /**
     * 初始化注释模版固定数据
     *
     * @param dataModel 模版数据
     * @param properties      配置
     * @param dateFormat      日期格式
     */
    public static void initDataModel(Map<String, Object> dataModel, Properties properties, SimpleDateFormat dateFormat) {
        properties.forEach((k, v) -> dataModel.put((String) k, v));
        dataModel.put("createDate", getDateString(dateFormat));
    }

    /**
     * 初始化注释模版数据
     *
     * @param dataModel    模版数据
     * @param introspectedTable  表
     * @param introspectedColumn 列
     */
    public static void initDataModel(Map<String, Object> dataModel, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

        // 是否是主键
        dataModel.put("isPK", isPK(introspectedTable, introspectedColumn));
        initDataModel(dataModel, introspectedColumn);
        initDataModel(dataModel, introspectedTable);
    }

    /**
     * 初始化注释模版数据
     *
     * @param dataModel    模版数据
     * @param introspectedColumn 列
     */
    public static void initDataModel(Map<String, Object> dataModel, IntrospectedColumn introspectedColumn) {
        // 列注释
        dataModel.put("columnRemark", introspectedColumn.getRemarks());
        // 列名称
        dataModel.put("columnName", introspectedColumn.getActualColumnName());
        // 是否还是 bold 列
        dataModel.put("isBold", introspectedColumn.isBLOBColumn());
        // 是否是自增列
        dataModel.put("isAutoIncrement", introspectedColumn.isAutoIncrement());
        // 是否是序列列
        dataModel.put("isSequenceColumn", introspectedColumn.isSequenceColumn());
        // 是否是 字符串 列
        dataModel.put("isStringColumn", introspectedColumn.isStringColumn());
        // 是否是 char 列
        dataModel.put("isJdbcCharacterColumn", introspectedColumn.isJdbcCharacterColumn());
        // 是否是 日期 列
        dataModel.put("isJDBCDateColumn", introspectedColumn.isJDBCDateColumn());
        // 是否是 时间 列
        dataModel.put("isJDBCTimeColumn", introspectedColumn.isJDBCTimeColumn());
        // 是否可为空
        dataModel.put("isNull", introspectedColumn.isNullable());
        // 默认值
        dataModel.put("defaultValue", introspectedColumn.getDefaultValue());
        // jdbcType名称
        dataModel.put("jdbcTypeName", introspectedColumn.getJdbcTypeName());
        // javaProperty 名称
        dataModel.put("javaProperty", introspectedColumn.getJavaProperty());
        // javaType名称
        dataModel.put("javaTypeName", introspectedColumn.getFullyQualifiedJavaType().getShortName());
    }

    /**
     * 初始化注释模版数据
     *
     * @param dataModel 模版数据
     * @param method          方法
     */
    public static void initDataModel(Map<String, Object> dataModel, Method method) {
        boolean isVoid = !method.getReturnType().isPresent();
        dataModel.put("methodName", method.getName());
        dataModel.put("isVoid", isVoid);
        dataModel.put("parameters", method.getParameters());
        if (method.getParameters().size() > 0) {
            dataModel.put("parameterName", method.getParameters().get(0).getName());
        }
        if (!isVoid) {
            dataModel.put("returnTypeName", method.getReturnType().get().getShortName());
            dataModel.put("returnType", method.getReturnType());
        }
    }

    /**
     * 初始化注释模版数据
     *
     * @param dataModel   模版数据
     * @param introspectedTable 表
     * @param method            方法
     */
    public static void initDataModel(Map<String, Object> dataModel, IntrospectedTable introspectedTable, Method method) {
        initDataModel(dataModel, introspectedTable);
        initDataModel(dataModel, method);
    }

    /**
     * 初始化注释模版数据
     *
     * @param dataModel   模版数据
     * @param introspectedTable 表
     */
    public static void initDataModel(Map<String, Object> dataModel, IntrospectedTable introspectedTable) {
        dataModel.put("tableName", introspectedTable.getFullyQualifiedTable().getIntrospectedTableName());
        dataModel.put("tableType", introspectedTable.getTableType());
        dataModel.put("tableAlias", introspectedTable.getFullyQualifiedTable().getAlias());
        dataModel.put("table", introspectedTable.getFullyQualifiedTable());
    }

    /**
     * 初始化模版数据
     *
     * @param dataModel   模版数据
     * @param compilationUnit   编译单元
     * @param introspectedTable 表
     */
    public static void initDataModel(Map<String, Object> dataModel, CompilationUnit compilationUnit, IntrospectedTable introspectedTable) {
        initDataModel(dataModel, compilationUnit);
        initDataModel(dataModel, introspectedTable);
    }

    public static void initDataModel(Map<String, Object> dataModel, CompilationUnit compilationUnit) {
        dataModel.put("className", compilationUnit.getType().getShortName());
    }

    /**
     * 初始化注释模版数据
     *
     * @param dataModel   模版数据
     * @param field             字段
     * @param introspectedTable 表
     */
    public static void initDataModel(Map<String, Object> dataModel, Field field, IntrospectedTable introspectedTable) {
        dataModel.put("fieldName", field.getName());
        dataModel.put("fileType", field.getType().getShortName());
        initDataModel(dataModel, introspectedTable);
    }

    /**
     * 初始化注释模版数据
     *
     * @param dataModel   模版数据
     * @param innerClass        内部类
     * @param introspectedTable 表
     */
    public static void initDataModel(Map<String, Object> dataModel, InnerClass innerClass, IntrospectedTable introspectedTable) {
        dataModel.put("innerClassName", innerClass.getType().getShortName());
        initDataModel(dataModel, introspectedTable);
    }

    /**
     * 判断是否是主键
     *
     * @param introspectedTable  表
     * @param introspectedColumn 列
     * @return 是否是主键
     */
    private static boolean isPK(IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String columnName = introspectedColumn.getActualColumnName();
        List<IntrospectedColumn> primaryKey = introspectedTable.getPrimaryKeyColumns();
        for (IntrospectedColumn pk : primaryKey) {
            if (columnName.equals(pk.getActualColumnName())) {
                return true;
            }
        }
        return false;
    }

}
