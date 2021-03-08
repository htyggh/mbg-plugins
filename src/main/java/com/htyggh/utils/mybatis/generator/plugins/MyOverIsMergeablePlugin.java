package com.htyggh.utils.mybatis.generator.plugins;

import org.mybatis.generator.api.GeneratedXmlFile;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;

import java.lang.reflect.Field;
import java.util.List;

/**
 * <p>标题：MyOverIsMergeablePlugin</p>
 * <p>描述：自定义MybatisGenerator文件覆盖插件</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2018年09月12日15时08分</p>
 * <p>版本：</p>
 */
public class MyOverIsMergeablePlugin extends PluginAdapter {

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

    @Override
    public boolean sqlMapGenerated(GeneratedXmlFile sqlMap, IntrospectedTable introspectedTable) {
        try {
            Field field = sqlMap.getClass().getDeclaredField("isMergeable");
            field.setAccessible(true);
            field.setBoolean(sqlMap, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


}
