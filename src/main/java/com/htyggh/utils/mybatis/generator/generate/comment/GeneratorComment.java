package com.htyggh.utils.mybatis.generator.generate.comment;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.JavaElement;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import static org.mybatis.generator.config.MergeConstants.NEW_ELEMENT_TAG;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;

import static com.htyggh.utils.mybatis.generator.utils.CommentGeneratorUtils.getDateString;

/**
 * <p>标题：GeneratorComment</p>
 * <p>描述：注释生成接口</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年06月04日09时34分</p>
 * <p>版本：</p>
 */
public interface GeneratorComment {

    /**
     * 加载配置
     *
     * @param properties 配置信息
     */
    void loadConfigurationProperties(Properties properties) throws Exception;

    /**
     * 添加pojo类，类注释
     *
     * @param topLevelClass     类
     * @param introspectedTable 表
     */
    void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable);

    /**
     * 添加Mapper接口类，注释
     *
     * @param mapper mapper接口
     */
    void addMapperClassComment(Interface mapper);

    /**
     * 添加Mapper接口类，注释
     *
     * @param mapper            mapper接口
     * @param introspectedTable 表对象
     */
    void addMapperClassComment(Interface mapper, IntrospectedTable introspectedTable);

    /**
     * 添加sqlProvider类，类注释
     *
     * @param sqlProvider sqlProvider类
     */
    void addSqlProviderClassComment(CompilationUnit sqlProvider);

    /**
     * 添加DSL支持类 类注释
     *
     * @param topLevelClass     DSL支持类
     * @param introspectedTable 表
     */
    void addDynamicSqlSupportClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable);

    /**
     * 添加DSL支持类 字段注释
     *
     * @param field              字段
     * @param introspectedTable  表
     * @param introspectedColumn 列
     */
    void addDynamicSqlSupportClassFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn);

    /**
     * 添加DSL支持类 内部类注释
     *
     * @param innerClass        内部类
     * @param introspectedTable 表
     */
    void addDynamicSqlSupportInnerClassComment(InnerClass innerClass, IntrospectedTable introspectedTable);

    /**
     * 添加DSL支持类 内部类字段注释
     *
     * @param field              字段
     * @param introspectedTable  表
     * @param introspectedColumn 列
     */
    void addDynamicSqlSupportInnerClassFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn);

    /**
     * 添加内部类，类注释
     *
     * @param innerClass        内部类
     * @param introspectedTable 表
     */
    void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable);

    /**
     * 添加内部类注释
     *
     * @param innerClass        内部类
     * @param introspectedTable 表
     * @param markAsDoNotDelete 是否添加不可删除标识
     */
    void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete);

    /**
     * 添加枚举类，类注释
     *
     * @param innerEnum         枚举类
     * @param introspectedTable 表
     */
    void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable);

    /**
     * 添加pojo类字段注释
     *
     * @param field              字段
     * @param introspectedTable  表
     * @param introspectedColumn 列
     */
    void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn);

    /**
     * 添加内部类字段注释
     *
     * @param field             字段
     * @param introspectedTable 表
     */
    void addFieldComment(Field field, IntrospectedTable introspectedTable);

    /**
     * 添加get方法注释
     *
     * @param method             方法
     * @param introspectedTable  表
     * @param introspectedColumn 列
     */
    void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn);

    /**
     * 添加set方法注释
     *
     * @param method             方法
     * @param introspectedTable  表
     * @param introspectedColumn 列
     */
    void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn);

    /**
     * 添加文件注释
     *
     * @param compilationUnit 编译单元
     */
    void addJavaFileComment(CompilationUnit compilationUnit);

    /**
     * 添加mapper.xml文件元素注释
     *
     * @param xmlElement xml元素
     */
    public void addComment(XmlElement xmlElement);

    /**
     * 添加mapper.xml文件根元素注释
     *
     * @param rootElement 根元素
     */
    void addRootComment(XmlElement rootElement);

    /**
     * 向方法添加注解
     *
     * @param method            方法
     * @param introspectedTable 表
     * @param imports           import集合
     * @since 1.3.6
     */
    void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports);

    /**
     * 向方法添加注解
     *
     * @param method             方法
     * @param introspectedTable  表
     * @param introspectedColumn 列
     * @param imports            import集合
     * @since 1.3.6
     */
    void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports);

    /**
     * 向字段添加注解
     *
     * @param field             字段
     * @param introspectedTable 表
     * @param imports           import集合
     * @since 1.3.6
     */
    void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports);

    /**
     * 向字段添加注解
     *
     * @param field              字段
     * @param introspectedTable  表
     * @param introspectedColumn 列
     * @param imports            import集合
     * @since 1.3.6
     */
    void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports);

    /**
     * 向内部类添加注解
     *
     * @param innerClass        内部类
     * @param introspectedTable 表
     * @param imports           import集合
     * @since 1.3.6
     */
    void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports);

    /**
     * 添加mapper类方法注释
     *
     * @param method            方法
     * @param introspectedTable 表
     */
    void addMapperMethodComment(Method method, IntrospectedTable introspectedTable);

    /**
     * 添加Example类方法注释
     *
     * @param method            方法
     * @param introspectedTable 表
     */
    void addExampleMethodComment(Method method, IntrospectedTable introspectedTable);

    /**
     * 此方法添加自定义javadoc标记。如果不希望包含Javadoc标记，您可以什么也不做。但是如果不包含Javadoc标记，那么eclipse插件的Java合并功能就会崩溃。
     *
     * @param javaElement       java对象
     * @param dateFormat        日期格式化对象
     * @param markAsDoNotDelete 是否标记为不删除
     */
    default void addJavadocTag(JavaElement javaElement, SimpleDateFormat dateFormat, boolean markAsDoNotDelete) {

        List<String> javaDocLines = javaElement.getJavaDocLines();

        StringBuilder sb = new StringBuilder(" *");
        sb.append(NEW_ELEMENT_TAG);

        if (markAsDoNotDelete) {
            sb.append(" do_not_delete_during_merge");
        }

        String s = getDateString(dateFormat);
        if (stringHasValue(s)) {
            sb.append(' ');
            sb.append(s);
        }
        if (javaDocLines.size() > 3) {
            javaDocLines.add(javaDocLines.size() - 2, " *");
            javaDocLines.add(javaDocLines.size() - 2, sb.toString());
        } else {
            javaElement.addJavaDocLine("/**");
            javaElement.addJavaDocLine(" *");
            javaElement.addJavaDocLine(sb.toString());
            javaElement.addJavaDocLine(" */");
        }

    }
}
