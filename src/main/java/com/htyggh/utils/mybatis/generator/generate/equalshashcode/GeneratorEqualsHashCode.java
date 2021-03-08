package com.htyggh.utils.mybatis.generator.generate.equalshashcode;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.List;

/**
 * <p>标题：GeneratorEqualsHashCode</p>
 * <p>描述：{@code Equals}和{@code HashCode}方法生成接口</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年05月17日13时21分</p>
 * <p>版本：</p>
 */
public interface GeneratorEqualsHashCode {

    /**
     * 生成Equals方法
     *
     * @param topLevelClass       java类对象
     * @param introspectedColumns 列对象
     * @param introspectedTable   表对象
     * @return Equals方法对象
     */
    Method generateEquals(TopLevelClass topLevelClass, List<IntrospectedColumn> introspectedColumns, IntrospectedTable introspectedTable);

    /**
     * 生成HashCode方法
     *
     * @param topLevelClass       java类对象
     * @param introspectedColumns 列对象
     * @param introspectedTable   表对象
     * @return HashCode方法对象
     */
    Method generateHashCode(TopLevelClass topLevelClass, List<IntrospectedColumn> introspectedColumns, IntrospectedTable introspectedTable);

}
