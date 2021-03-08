package com.htyggh.utils.mybatis.generator.utils;

/**
 * 字段名称格式转换器
 *
 * @author htyggh
 * @version 1.0
 * @date 2021年01月11日17时41分
 */
public class FiledNameConversion {

    /**
     * 蛇形命名法
     */
    private static final String CONVERSION_SNAKE_CASE = "snake_case";

    /**
     * 空格命名法
     */
    private static final String CONVERSION_SPACE_CASE = "space case";

    /**
     * 短横线命名法
     */
    private static final String CONVERSION_KEBAB_CASE = "kebab-case";

    /**
     * 大写蛇形命名法
     */
    private static final String CONVERSION_UPPER_SNAKE_CASE = "SNAKE_CASE";

    /**
     * 帕斯卡命名法(大驼峰命名法)
     */
    private static final String CONVERSION_PASCAL_CASE = "PascalCase";

    /**
     * 驼峰命名法
     */
    private static final String CONVERSION_CAMEL_CASE = "camelCase";

    /**
     * 小写蛇形命名法
     */
    private static final String CONVERSION_LOWER_SNAKE_CASE = "snake_case";

    /**
     * 将字符串从{@code CamelCase}格式转换为{@code snake_case}格式
     *
     * @param in {@code CamelCase}格式的字符串
     * @return {@code snake_case}格式的字符串
     */
    public static String toLowerSnakeCase(String in) {
        StringBuilder result = new StringBuilder("" + Character.toLowerCase(in.charAt(0)));
        for (int i = 1; i < in.length(); i++) {
            char c = in.charAt(i);
            if (Character.isUpperCase(c)) {
                result.append("_").append(Character.toLowerCase(c));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }

    /**
     * 将字符串从{@code CamelCase}格式转换为{@code SNAKE_CASE}格式
     *
     * @param in {@code CamelCase}格式的字符串
     * @return {@code snake_case}格式的字符串
     */
    public static String toUpperSnakeCase(String in) {
        return toLowerSnakeCase(in).toUpperCase();
    }

    /**
     * 将字符串从{@code snake_case}格式转换为{@code camelCase}格式
     *
     * @param in {@code snake_case}格式的字符串
     * @return {@code camelCase}格式的字符串
     */
    public static String toCamelCase(String in) {
        StringBuilder camelCased = new StringBuilder();
        String[] tokens = in.split("_");
        for (int i = 0, tokensLength = tokens.length; i < tokensLength; i++) {
            String token = tokens[i];
            if (token.length() >= 1) {
                if (i != 0) {
                    camelCased.append(token.substring(0, 1).toUpperCase()).append(token.substring(1).toLowerCase());
                } else {
                    camelCased.append(token.toLowerCase());
                }
            } else {
                camelCased.append("_");
            }
        }
        return camelCased.toString();
    }

}
