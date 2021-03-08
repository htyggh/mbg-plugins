package com.htyggh.utils.mybatis.generator.registry;

import org.mybatis.generator.config.PropertyRegistry;

/**
 * <p>标题：MyPropertyRegistry</p>
 * <p>描述：自定义常量注册类</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2018年09月15日13时20分</p>
 * <p>版本：</p>
 */
public class MyPropertyRegistry {

    /**
     * 注释模版类
     */
    public static final String COMMENT_TYPE = "commentType";

    /**
     * 文件注释模版路径
     */
    public static final String FILE_COMMENT_PATH = "fileCommentPath";

    /**
     * 是否不生成所有自定义注释
     */
    public static final String COMMENT_GENERATOR_SUPPRESS_ALL_MY_COMMENTS = "suppressAllComments";

    /**
     * 是否不生成文件注释
     */
    public static final String COMMENT_GENERATOR_SUPPRESS_JAVA_FILE_MY_COMMENTS = "suppressJavaFileComments";

    /**
     * DSL支持类 内部类 访问控制符
     */
    public static final String DSL_INNERCLASS_VISIBILITY = "innerClassVisibility";

    /**
     * DSL 支持类 内部类 字段 访问控制符
     */
    public static final String DSL_INNERCLASS_FIELD_VISIBILITY = "innerClassFieldVisibility";

    /**
     * DSL 支持类 内部类 构造方法 访问控制符
     */
    public static final String DSL_INNERCLASS_METHOD_VISIBILITY = "innerClassMethodVisibility";

    /**
     * {@code equals} {@code hashCode} 方法生成器实现类
     */
    public static final String EQUALS_HASHCODE_TYPE = "equalsHashCodeType";

    /**
     * {@code equals} {@code hashCode} 方法忽略字段 以','分割的字符串
     */
    public static final String EQUALS_HASHCODE_IGNORE_FIELD = "equalsHashCodeIgnoreField";

    /**
     * {@code equals} {@code hashCode} 方法忽略字段 正则表达式
     */
    public static final String EQUALS_HASHCODE_IGNORE_FIELD_BY_REGEX = "equalsHashCodeIgnoreFieldByRegex";

    /**
     * 是否抑制父级的字段忽略规则
     */
    public static final String SUPPRESS_EQUALS_HASHCODE_PARENT_IGNORE = "suppressEqualsHashCodeParentIgnore";

    /**
     * {@code equals} {@code hashCode} 方法包含字段 以','分割的字符串(该属性被设置后会抑制所有忽略规则)
     */
    public static final String EQUALS_HASHCODE_INCLUDED =  "equalsHashCodeIncluded";
}
