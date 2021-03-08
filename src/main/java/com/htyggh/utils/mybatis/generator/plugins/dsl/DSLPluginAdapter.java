package com.htyggh.utils.mybatis.generator.plugins.dsl;

import org.mybatis.generator.api.PluginAdapter;

import java.util.List;

import static com.htyggh.utils.mybatis.generator.constant.BaseConst.MY_BATIS3_DYNAMIC_SQL;

/**
 * 该适配器实现了validate方法只有当选择的是{@code MyBatis3DynamicSQL}模式时才加载插件
 * @author htyggh
 * @version 1.0
 * @date 2021年01月13日17时48分
 */
public class DSLPluginAdapter extends PluginAdapter {

    String targetRuntime;

    /**
     * 错误信息
     */
    List<String> warnings;

    /**
     * 当选择的时{@code MyBatis3DynamicSql}模式时才运行插件
     *
     * @param warnings 插件无效的原因
     * @return 如果插件有效返回true 无效则返回 false
     */
    @Override
    public boolean validate(List<String> warnings) {
        this.warnings = warnings;
        targetRuntime = context.getTargetRuntime();
        boolean validate = targetRuntime.toLowerCase().startsWith(MY_BATIS3_DYNAMIC_SQL.toLowerCase());
        if (!validate) {
            warnings.add(String.format("当前选择的模式不是：%s", MY_BATIS3_DYNAMIC_SQL));
        }
        return validate;
    }
}
