package com.htyggh.utils.mybatis.generator.utils;

import org.mybatis.generator.api.IntrospectedColumn;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static org.mybatis.generator.internal.util.StringUtility.stringHasValue;
import static org.mybatis.generator.internal.util.StringUtility.isTrue;

import static com.htyggh.utils.mybatis.generator.registry.MyPropertyRegistry.EQUALS_HASHCODE_IGNORE_FIELD;
import static com.htyggh.utils.mybatis.generator.registry.MyPropertyRegistry.EQUALS_HASHCODE_IGNORE_FIELD_BY_REGEX;
import static com.htyggh.utils.mybatis.generator.registry.MyPropertyRegistry.EQUALS_HASHCODE_INCLUDED;
import static com.htyggh.utils.mybatis.generator.registry.MyPropertyRegistry.SUPPRESS_EQUALS_HASHCODE_PARENT_IGNORE;

/**
 * <p>标题：EqualsHashCodeUtils</p>
 * <p>描述：生成Equals HashCode方法的辅助工具类</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年05月27日09时16分</p>
 * <p>版本：</p>
 */
public class EqualsHashCodeUtils {

    /**
     * 工具类不需要示例化
     */
    private EqualsHashCodeUtils() {

    }

    private static final String USE_GETTER = "useGetter";

    private static final String IGNORE_FIELD = "ignoreField";

    private static final String IGNORE_FIELD_BY_REGEX = "ignoreFieldByRegex";

    /**
     * 使用使用getter方法生成Equals HashCode方法
     */
    private static boolean useGetter;

    /**
     * 全局忽略字段集合
     */
    private static String[] ignoreField;

    /**
     * 全局忽略字段正则表达式
     */
    private static String ignoreFieldByRegex;

    /**
     * 初始化全局配置
     *
     * @param properties 全局配置
     */
    public static void init(Properties properties) {
        useGetter = isTrue(properties.getProperty(USE_GETTER, "false"));
        String ignoreString = properties.getProperty(IGNORE_FIELD);
        ignoreFieldByRegex = properties.getProperty(IGNORE_FIELD_BY_REGEX);
        if (stringHasValue(ignoreString)) {
            ignoreField = ignoreString.split(",");
            ignoreField = Arrays.stream(ignoreField).distinct().toArray(String[]::new);
        }

    }

    /**
     * 根据配置过滤列
     *
     * @param introspectedColumns 列集合
     * @param properties          配置信息
     * @return 过滤后的列集合
     */
    public static List<IntrospectedColumn> filter(List<IntrospectedColumn> introspectedColumns, Properties properties) {

        boolean suppressEqualsHashCodeParentIgnore = isTrue(properties.getProperty(SUPPRESS_EQUALS_HASHCODE_PARENT_IGNORE, "false"));

        String equalsHashCodeIgnoreString = properties.getProperty(EQUALS_HASHCODE_IGNORE_FIELD);
        String equalsHashCodeIgnoreFieldByRegex = properties.getProperty(EQUALS_HASHCODE_IGNORE_FIELD_BY_REGEX);
        String equalsHashCodeIncludedString = properties.getProperty(EQUALS_HASHCODE_INCLUDED);

        String[] equalsHashCodeIgnoreField = null;
        String[] equalsHashCodeIncluded = null;

        if (stringHasValue(equalsHashCodeIgnoreString)) {
            equalsHashCodeIgnoreField = equalsHashCodeIgnoreString.split(",");
        }

        if (stringHasValue(equalsHashCodeIncludedString)) {
            equalsHashCodeIncluded = equalsHashCodeIncludedString.split(",");
        }

        if (equalsHashCodeIncluded != null && equalsHashCodeIncluded.length > 0) {
            introspectedColumns = filter(introspectedColumns, equalsHashCodeIncluded);
        } else {
            introspectedColumns = filter(introspectedColumns, suppressEqualsHashCodeParentIgnore);
            introspectedColumns = filter(introspectedColumns, equalsHashCodeIgnoreFieldByRegex, equalsHashCodeIgnoreField);
        }

        return introspectedColumns;
    }

    /**
     * 根据配置过滤列
     *
     * @param introspectedColumns 列集合
     * @param fields              需要保留的列
     * @return 过滤后的列集合
     */
    private static List<IntrospectedColumn> filter(List<IntrospectedColumn> introspectedColumns, final String[] fields) {
        introspectedColumns = introspectedColumns.stream().filter(introspectedColumn -> {
            boolean filter = false;
            for (String s : fields) {
                if (introspectedColumn.getActualColumnName().equalsIgnoreCase(s.trim())) {
                    filter = true;
                }
            }
            return filter;
        }).collect(Collectors.toList());
        return introspectedColumns;
    }

    /**
     * 根据全局配置过滤列
     *
     * @param introspectedColumns                列集合
     * @param suppressEqualsHashCodeParentIgnore 是否忽略全局配置
     * @return 过滤后的列
     */
    private static List<IntrospectedColumn> filter(List<IntrospectedColumn> introspectedColumns, boolean suppressEqualsHashCodeParentIgnore) {
        if (!suppressEqualsHashCodeParentIgnore) {
            introspectedColumns = filter(introspectedColumns, ignoreFieldByRegex, ignoreField);
        }
        return introspectedColumns;
    }

    /**
     * 根据配置过滤列
     *
     * @param introspectedColumns 列集合
     * @param regex               正则表达式
     * @param fields              需要过滤的列
     * @return 过滤后的列
     */
    private static List<IntrospectedColumn> filter(List<IntrospectedColumn> introspectedColumns, final String regex, final String[] fields) {

        if (stringHasValue(regex)) {
            introspectedColumns = introspectedColumns.stream().filter(introspectedColumn -> !introspectedColumn.getActualColumnName().matches(ignoreFieldByRegex)).collect(Collectors.toList());
        }

        if (fields != null && fields.length > 0) {
            introspectedColumns = introspectedColumns.stream().filter(introspectedColumn -> {
                boolean filter = true;
                for (String s : fields) {
                    if (introspectedColumn.getActualColumnName().equalsIgnoreCase(s.trim())) {
                        filter = false;
                    }
                }
                return filter;
            }).collect(Collectors.toList());
        }

        return introspectedColumns;
    }

}
