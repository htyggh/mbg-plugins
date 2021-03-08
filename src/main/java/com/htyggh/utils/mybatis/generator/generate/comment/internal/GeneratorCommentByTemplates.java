package com.htyggh.utils.mybatis.generator.generate.comment.internal;

import com.htyggh.utils.mybatis.generator.generate.comment.GeneratorComment;
import com.htyggh.utils.mybatis.generator.templates.comment.CommentTemplate;
import com.htyggh.utils.mybatis.generator.templates.comment.internal.velocity.DefaultCommentTemplate;
import com.htyggh.utils.mybatis.generator.templates.engine.TemplateContainer;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.htyggh.utils.mybatis.generator.registry.MyPropertyRegistry.*;
import static com.htyggh.utils.mybatis.generator.utils.CommentGeneratorUtils.initDataModel;
import static com.htyggh.utils.mybatis.generator.utils.CommentGeneratorUtils.splitComment;
import static org.mybatis.generator.config.PropertyRegistry.COMMENT_GENERATOR_DATE_FORMAT;
import static org.mybatis.generator.internal.util.StringUtility.isTrue;
import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.ObjectFactory.createInternalObject;

/**
 * <p>标题：GeneratorCommentByTemplates</p>
 * <p>描述：通过模版的生成注释</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年06月05日10时27分</p>
 * <p>版本：</p>
 */
public class GeneratorCommentByTemplates implements GeneratorComment {

    /**
     * 注释模版工厂
     */
    private CommentTemplate commentTemplate;

    /**
     * 文件注释模版
     */
    private TemplateContainer<?> fileCommentTemplate;

    /**
     * 模版数据
     */
    private final Map<String, Object> context;

    /**
     * 时间格式
     */
    private SimpleDateFormat dateFormat;

    /**
     * 是否不生成所有自定义注释
     */
    private boolean suppressAllComments;

    /**
     * 是否不生成文件注释
     */
    private boolean suppressJavaFileComments;

    private static final String FIELD_TYPE_NAME = "SqlColumn";

    public GeneratorCommentByTemplates() {
        super();

        // 实例化context
        this.context = new HashMap<>();

        // 设置默认的日期格式
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        // 默认情况下不屏蔽所有注释
        this.suppressAllComments = false;

        // 默认情况下屏蔽文件注释
        this.suppressJavaFileComments = true;
    }

    /**
     * 加载配置
     *
     * @param properties 配置信息
     */
    @Override
    public void loadConfigurationProperties(Properties properties) throws Exception {

        // 获取配置文件的 suppressAllComments 属性 若 suppressAllComments 属性为true则不生成自定义注释
        String suppressAll = properties.getProperty(COMMENT_GENERATOR_SUPPRESS_ALL_MY_COMMENTS);
        if (stringHasValue(suppressAll)) {
            suppressAllComments = isTrue(suppressAll);
        }

        // 获取配置文件的 suppressJavaFileComments 属性 若 suppressJavaFileComments 属性为true则不生成自定义文件注释
        String suppressFile = properties.getProperty(COMMENT_GENERATOR_SUPPRESS_JAVA_FILE_MY_COMMENTS);
        if (stringHasValue(suppressFile)) {
            suppressJavaFileComments = isTrue(suppressFile);
        }

        // 获取配置文件的 dateFormat（日期格式） 属性
        String dateFormatString = properties.getProperty(COMMENT_GENERATOR_DATE_FORMAT);

        if (stringHasValue(dateFormatString)) {
            //若配置文件的 dateFormat（日期格式）不为空则使用配置文件内配置的日期格式
            dateFormat = new SimpleDateFormat(dateFormatString);
        }

        /*
         * 获取配置文件的 commentType（注释类） 属性
         * 本程序提供两种缺省方式提供选择
         * 1.com.htyggh.com.htyggh.utils.mybatis.generator.generate.comment.internal.DefaultCommentTemplate
         * 2.com.htyggh.com.htyggh.utils.mybatis.generator.generate.comment.internal.SimpleCommentTemplate
         * 默认情况下使用第一种
         */
        String commentType = properties.getProperty(COMMENT_TYPE);

        // 获取配置文件的 fileCommentPath（文件注释模版路径） 属性
        String fileCommentPath = properties.getProperty(FILE_COMMENT_PATH);

        if (stringHasValue(dateFormatString)) {
            //若配置文件的 dateFormat（日期格式）不为空则使用配置文件内配置的日期格式
            dateFormat = new SimpleDateFormat(dateFormatString);
        }

        if (!stringHasValue(commentType)) {
            // 在配置文件commentType（注释模版类） 属性缺失的情况下使用默认注释模版
            commentType = DefaultCommentTemplate.class.getName();
        }

        commentTemplate = (CommentTemplate) createInternalObject(commentType);

        if (!suppressJavaFileComments) {
            if (!stringHasValue(fileCommentPath)) {
                this.fileCommentTemplate = commentTemplate.getFileCommentTemPlate();
            } else {
                this.fileCommentTemplate = commentTemplate.getTemplateEngine().getTemplate(fileCommentPath);
            }
        }

        initDataModel(context, properties, dateFormat);
    }

    /**
     * 添加pojo类，类注释
     *
     * @param topLevelClass     类
     * @param introspectedTable 表
     */
    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        topLevelClass.getJavaDocLines().clear();
        initDataModel(context, (CompilationUnit) topLevelClass, introspectedTable);
        String[] comments = splitComment(context, commentTemplate.getModelClassCommentTemplate());
        for (String comment : comments) {
            topLevelClass.addJavaDocLine(comment);
        }
    }

    /**
     * 添加Mapper接口类，注释
     *
     * @param mapper mapper接口
     */
    @Override
    public void addMapperClassComment(Interface mapper) {
        if (suppressAllComments) {
            return;
        }
        mapper.getJavaDocLines().clear();
        initDataModel(context, mapper);
        String[] comments = splitComment(context, commentTemplate.getMapperClassCommentTemplate());
        for (String comment : comments) {
            mapper.addJavaDocLine(comment);
        }
    }

    /**
     * 添加Mapper接口类，注释
     *
     * @param mapper            mapper接口
     * @param introspectedTable 表对象
     */
    @Override
    public void addMapperClassComment(Interface mapper, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }

        mapper.getJavaDocLines().clear();
        initDataModel(context, mapper);
        String[] comments = splitComment(context, commentTemplate.getMapperClassCommentTemplate());

        for (String comment : comments) {
            mapper.addJavaDocLine(comment);
        }

        for (Method method : mapper.getMethods()) {
            addMapperMethodComment(method, introspectedTable);
        }

        addJavaFileComment(mapper);
    }

    /**
     * 添加sqlProvider类，类注释
     *
     * @param sqlProvider sqlProvider类
     */
    @Override
    public void addSqlProviderClassComment(CompilationUnit sqlProvider) {
        if (suppressAllComments) {
            return;
        }
        initDataModel(context, sqlProvider);
        String[] comments = splitComment(context, commentTemplate.getSqlProviderClassCommentTemplate());
        JavaElement javaElement = (JavaElement) sqlProvider;
        javaElement.getJavaDocLines().clear();
        for (String comment : comments) {
            javaElement.addJavaDocLine(comment);
        }
    }

    /**
     * 添加DSL支持类 类注释
     *
     * @param topLevelClass     DSL 支持类
     * @param introspectedTable 表
     */
    @Override
    public void addDynamicSqlSupportClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        topLevelClass.getJavaDocLines().clear();
        initDataModel(context, (CompilationUnit) topLevelClass, introspectedTable);
        String[] comments = splitComment(context, commentTemplate.getDSLSupportClassCommentTemplate());
        for (String comment : comments) {
            topLevelClass.addJavaDocLine(comment);
        }

        addDynamicSqlSupportInnerClassComment(topLevelClass.getInnerClasses().get(0), introspectedTable);

        List<Field> fields = topLevelClass.getFields().stream().filter(field -> FIELD_TYPE_NAME.equals(field.getType().getShortNameWithoutTypeArguments())).collect(Collectors.toList());
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();

        for (int i = 0; i < fields.size(); i++) {
            addDynamicSqlSupportClassFieldComment(fields.get(i), introspectedTable, allColumns.get(i));
        }
    }

    /**
     * 添加DSL支持类 字段注释
     *
     * @param field              字段
     * @param introspectedTable  表
     * @param introspectedColumn 列
     */
    @Override
    public void addDynamicSqlSupportClassFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        field.getJavaDocLines().clear();
        initDataModel(context, introspectedTable, introspectedColumn);
        String[] comments = splitComment(context, commentTemplate.getDSLSupportClassFieldCommentTemplate());
        for (String comment : comments) {
            field.addJavaDocLine(comment);
        }
    }

    /**
     * 添加DSL支持类 内部类注释
     *
     * @param innerClass        内部类
     * @param introspectedTable 表
     */
    @Override
    public void addDynamicSqlSupportInnerClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        innerClass.getJavaDocLines().clear();
        initDataModel(context, innerClass, introspectedTable);
        String[] comments = splitComment(context, commentTemplate.getDSLSupportInnerClassCommentTemplate());
        for (String comment : comments) {
            innerClass.addJavaDocLine(comment);
        }

        List<Field> fields = innerClass.getFields();
        List<IntrospectedColumn> allColumns = introspectedTable.getAllColumns();

        for (int i = 0; i < fields.size(); i++) {
            addDynamicSqlSupportInnerClassFieldComment(fields.get(i), introspectedTable, allColumns.get(i));
        }
    }

    /**
     * 添加DSL支持类 内部类字段注释
     *
     * @param field              字段
     * @param introspectedTable  表
     * @param introspectedColumn 列
     */
    @Override
    public void addDynamicSqlSupportInnerClassFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        if (suppressAllComments) {
            return;
        }
        field.getJavaDocLines().clear();
        initDataModel(context, introspectedTable, introspectedColumn);
        String[] comments = splitComment(context, commentTemplate.getDSLSupportInnerClassFieldCommentTemplate());
        for (String comment : comments) {
            field.addJavaDocLine(comment);
        }
    }

    /**
     * 添加内部类，类注释
     *
     * @param innerClass        内部类
     * @param introspectedTable 表
     */
    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
        innerClass.getJavaDocLines().clear();
        initDataModel(context, innerClass, introspectedTable);
        String[] comments = splitComment(context, commentTemplate.getExampleInnerClassCommentTemplate());
        for (String comment : comments) {
            innerClass.addJavaDocLine(comment);
        }
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
        addClassComment(innerClass, introspectedTable);
        addJavadocTag(innerClass, dateFormat, markAsDoNotDelete);
    }

    /**
     * 添加枚举类，类注释
     *
     * @param innerEnum         枚举类
     * @param introspectedTable 表
     */
    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }
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
        if (suppressAllComments) {
            return;
        }

        field.getJavaDocLines().clear();
        initDataModel(context, introspectedTable, introspectedColumn);
        String[] comments = splitComment(context, commentTemplate.getFieldCommentTemplate());

        for (String comment : comments) {
            field.addJavaDocLine(comment);
        }
    }

    /**
     * 添加内部类字段注释
     *
     * @param field             字段
     * @param introspectedTable 表
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }

        // TODO 改为序列化变量
        if (field.isFinal()) {
            return;
        }

        field.getJavaDocLines().clear();

        initDataModel(context, field, introspectedTable);
        String[] comments = splitComment(context, commentTemplate.getExampleFieldCommentTemplate());
        for (String comment : comments) {
            field.addJavaDocLine(comment);
        }
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
        if (suppressAllComments) {
            return;
        }

        method.getJavaDocLines().clear();
        initDataModel(context, introspectedTable, introspectedColumn);
        initDataModel(context, method);
        String[] comments;
        comments = splitComment(context, commentTemplate.getModelGetMethodCommentTemplate());

        for (String comment : comments) {
            method.addJavaDocLine(comment);
        }
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
        if (suppressAllComments) {
            return;
        }

        method.getJavaDocLines().clear();
        initDataModel(context, introspectedTable, introspectedColumn);
        initDataModel(context, method);
        String[] comments;
        comments = splitComment(context, commentTemplate.getModelSetMethodCommentTemplate());

        for (String comment : comments) {
            method.addJavaDocLine(comment);
        }
    }

    /**
     * 添加文件注释
     *
     * @param compilationUnit 编译单元
     */
    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {
        if (suppressAllComments) {
            return;
        }

        compilationUnit.getFileCommentLines().clear();

        if (!suppressJavaFileComments) {
            initDataModel(context, compilationUnit);
            String[] comments = splitComment(context, fileCommentTemplate);
            for (String comment : comments) {
                compilationUnit.addFileCommentLine(comment);
            }
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
     * 添加mapper类方法注释
     *
     * @param method            方法
     * @param introspectedTable 表
     */
    @Override
    public void addMapperMethodComment(Method method, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }

        method.getJavaDocLines().clear();
        initDataModel(context, introspectedTable, method);
        String[] comments = splitComment(context, commentTemplate.getMapperMethodCommentTemplate());

        for (String comment : comments) {
            method.addJavaDocLine(comment);
        }
    }

    /**
     * 添加Example类方法注释
     *
     * @param method            方法
     * @param introspectedTable 表
     */
    @Override
    public void addExampleMethodComment(Method method, IntrospectedTable introspectedTable) {
        if (suppressAllComments) {
            return;
        }

        method.getJavaDocLines().clear();
        initDataModel(context, introspectedTable, method);
        String[] comments = splitComment(context, commentTemplate.getExampleMethodCommentTemplate());

        for (String comment : comments) {
            method.addJavaDocLine(comment);
        }
    }

}
