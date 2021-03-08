package com.htyggh.utils.mybatis.generator.comment;

import com.htyggh.utils.mybatis.generator.generate.comment.GeneratorComment;
import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.CompilationUnit;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.InnerClass;
import org.mybatis.generator.api.dom.java.InnerEnum;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.Properties;
import java.util.Set;

import static com.htyggh.utils.mybatis.generator.generate.GenerateFactory.getGeneratorComment;


/**
 * <p>标题：MyCommentGenerator</p>
 * <p>描述：自定义MybatisGenerator注释插件</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2018年09月13日14时35分</p>
 * <p>版本：</p>
 */
public class MyCommentGenerator implements CommentGenerator {

    /**
     * 注释模版工厂
     */
    private GeneratorComment commentTemplate;

    /**
     * 读取配置
     *
     * @param properties 配置信息
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
        commentTemplate = getGeneratorComment("com.htyggh.utils.mybatis.generator.generate.comment.internal.GeneratorCommentByTemplates");
        try {
            commentTemplate.loadConfigurationProperties(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 添加pojo类，类注释
     *
     * @param topLevelClass     类
     * @param introspectedTable 表
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        commentTemplate.addModelClassComment(topLevelClass, introspectedTable);
    }

    /**
     * 添加内部类注释
     *
     * @param innerClass        内部类
     * @param introspectedTable 表
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        commentTemplate.addClassComment(innerClass, introspectedTable);
    }

    /**
     * 添加内部类注释
     *
     * @param innerClass        内部类
     * @param introspectedTable 表
     * @param markAsDoNotDelete 是否添加不可删除标识
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean markAsDoNotDelete) {
        commentTemplate.addClassComment(innerClass, introspectedTable, markAsDoNotDelete);
    }

    /**
     * 添加枚举类注释
     *
     * @param innerEnum         枚举类
     * @param introspectedTable 表
     */
    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        commentTemplate.addEnumComment(innerEnum, introspectedTable);
    }

    /**
     * 添加pojo类字段注释
     *
     * @param field              字段
     * @param introspectedTable  表
     * @param introspectedColumn 列
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        commentTemplate.addFieldComment(field, introspectedTable, introspectedColumn);
    }

    /**
     * 添加内部类字段注释
     *
     * @param field             字段
     * @param introspectedTable 表
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        commentTemplate.addFieldComment(field, introspectedTable);
    }

    /**
     * 添加get方法注释
     *
     * @param method             方法
     * @param introspectedTable  表
     * @param introspectedColumn 列
     */
    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        commentTemplate.addGetterComment(method, introspectedTable, introspectedColumn);
    }

    /**
     * 添加set方法注释
     *
     * @param method             方法
     * @param introspectedTable  表
     * @param introspectedColumn 列
     */
    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        commentTemplate.addSetterComment(method, introspectedTable, introspectedColumn);
    }

    /**
     * 添加文件注释
     *
     * @param compilationUnit 编译单元
     */
    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {

        commentTemplate.addJavaFileComment(compilationUnit);

        /*
         * mapper类会进入此方法
         */
        if (compilationUnit instanceof Interface) {
            Interface mapper = (Interface) compilationUnit;
            commentTemplate.addMapperClassComment(mapper);
        }

        /*
         * sqlProvider 类会进入此方法
         */
        if (compilationUnit.getType().getShortName().matches("[\\w]*SqlProvider$")) {
            commentTemplate.addSqlProviderClassComment(compilationUnit);
        }
    }

    /**
     * 添加mapper.xml文件元素注释
     *
     * @param xmlElement xml元素
     */
    @Override
    public void addComment(XmlElement xmlElement) {
        //不向xml添加注释（xml注释在开发中无实际作用）
    }

    /**
     * 添加mapper.xml文件根元素注释
     *
     * @param rootElement 根元素
     */
    @Override
    public void addRootComment(XmlElement rootElement) {
        //不向xml文件添加注释（xml注释在开发中无实际作用）
    }

    /**
     * 向方法添加注解
     *
     * @param method            方法
     * @param introspectedTable 表
     * @param imports           import集合
     * @since 1.3.6
     */
    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        // 不向方法添加@Generated注解（@Generated注解在开发中无实际作用）
    }

    /**
     * 向方法添加注解
     *
     * @param method             方法
     * @param introspectedTable  表
     * @param introspectedColumn 列
     * @param imports            import集合
     * @since 1.3.6
     */
    @Override
    public void addGeneralMethodAnnotation(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
        // 不向方法添加@Generated注解（@Generated注解在开发中无实际作用）
    }

    /**
     * 向字段添加注解
     *
     * @param field             字段
     * @param introspectedTable 表
     * @param imports           import集合
     * @since 1.3.6
     */
    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        //不向字段添加@Generated注解（@Generated注解在开发中无实际作用）
    }

    /**
     * 向字段添加注解
     *
     * @param field              字段
     * @param introspectedTable  表
     * @param introspectedColumn 列
     * @param imports            import集合
     * @since 1.3.6
     */
    @Override
    public void addFieldAnnotation(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn, Set<FullyQualifiedJavaType> imports) {
        //不向字段添加@Generated注解（@Generated注解在开发中无实际作用）
    }

    /**
     * 向内部类添加注解
     *
     * @param innerClass        内部类
     * @param introspectedTable 表
     * @param imports           import集合
     * @since 1.3.6
     */
    @Override
    public void addClassAnnotation(InnerClass innerClass, IntrospectedTable introspectedTable, Set<FullyQualifiedJavaType> imports) {
        //不向内部类添加@Generated注解（@Generated注解在开发中无实际作用）
    }

    /**
     * 添加方法注释
     *
     * @param method            方法
     * @param introspectedTable 表
     */
    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

        String methodName = method.getName();

        // mapper类的方法 会进入此方法
        if (methodName.matches("^update[\\w]*|^delete[\\w]*|^insert[\\w]*|^select[\\w]*|^count[\\w]*|^apply[\\w]*")) {
            commentTemplate.addMapperMethodComment(method, introspectedTable);
        }
        // Example类的方法 会进入此方法
        else if (method.getAnnotations().isEmpty() && !method.isConstructor()) {
            commentTemplate.addExampleMethodComment(method, introspectedTable);
        }

    }
}
