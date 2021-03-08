package com.htyggh.utils.mybatis.generator.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.Interface;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.List;
import java.util.Properties;

import static com.htyggh.utils.mybatis.generator.constant.BaseConst.ANNOTATEDMAPPER;
import static com.htyggh.utils.mybatis.generator.constant.BaseConst.MY_BATIS3_DYNAMIC_SQL;

/**
 * <p>标题：AnnotationCachePlugin</p>
 * <p>描述：注解开发缓存插件</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年05月17日13时26分</p>
 * <p>版本：</p>
 */
public class AnnotationCachePlugin extends PluginAdapter {

    private final FullyQualifiedJavaType cacheNamespace = new FullyQualifiedJavaType("org.apache.ibatis.annotations.CacheNamespace");
    private final FullyQualifiedJavaType cacheNamespaceRef = new FullyQualifiedJavaType("org.apache.ibatis.annotations.CacheNamespaceRef");
    private final FullyQualifiedJavaType property = new FullyQualifiedJavaType("org.apache.ibatis.annotations.Property");

    public enum CacheProperty {
        EVICTION("cache_eviction", "eviction"),
        FLUSH_INTERVAL("cache_flushInterval", "flushInterval"),
        READ_ONLY("cache_readOnly", "readWrite"),
        SIZE("cache_size", "size"),
        TYPE("cache_type", "implementation"),
        BLOCKING("cache_blocking", "blocking"),
        PROPERTIES("cache_properties", "properties");
        private final String propertyName;
        private final String attributeName;

        CacheProperty(String propertyName, String attributeName) {
            this.propertyName = propertyName;
            this.attributeName = attributeName;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public String getAttributeName() {
            return attributeName;
        }
    }

    public enum CacheRefProperty {
        NAME("cache_ref_name", "name"),
        VALUE("cache_ref_value", "value");
        private final String propertyName;
        private final String attributeName;

        CacheRefProperty(String propertyName, String attributeName) {
            this.propertyName = propertyName;
            this.attributeName = attributeName;
        }

        public String getPropertyName() {
            return propertyName;
        }

        public String getAttributeName() {
            return attributeName;
        }
    }

    /**
     * 当选择的时 {@code MyBatis3DynamicSql} 模式时才运行此插件
     *
     * @param warnings 插件无效的原因
     * @return 如果插件有效返回true 无效则返回 false
     */
    @Override
    public boolean validate(List<String> warnings) {
        boolean validate = MY_BATIS3_DYNAMIC_SQL.equalsIgnoreCase(context.getTargetRuntime());
        validate |= ANNOTATEDMAPPER.equalsIgnoreCase(context.getJavaClientGeneratorConfiguration().getConfigurationType());
        if (!validate) {
            warnings.add(String.format("当前选择的模式不是：%s", MY_BATIS3_DYNAMIC_SQL));
        }
        return validate;
    }

    /**
     * 加载配置
     *
     * @param properties 配置信息
     */
    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);

    }

    /**
     * 添加加缓存注解
     *
     * @param interfaze         接口
     * @param introspectedTable 表
     * @return 如果需要生成字段，则返回true;如果需要忽略生成的字段，则返回false。在多个插件的情况下，第一个返回false的插件将禁用对其他插件的调用。
     */
    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        StringBuilder cacheNamespaceAnnotation = new StringBuilder("@CacheNamespace");
        StringBuilder cacheNamespaceRefAnnotation = new StringBuilder("@CacheNamespaceRef");
        interfaze.addImportedType(cacheNamespace);
        cacheNamespaceAnnotation.append("(");
        for (CacheProperty cacheProperty : CacheProperty.values()) {
            addAttributeIfExists(interfaze, cacheNamespaceAnnotation, introspectedTable, cacheProperty);
        }
        cacheNamespaceAnnotation.append(")");

        int index = cacheNamespaceAnnotation.lastIndexOf(",");
        if (index < 0) {
            cacheNamespaceAnnotation.delete(cacheNamespaceAnnotation.length() - 2, cacheNamespaceAnnotation.length());
        } else {
            cacheNamespaceAnnotation.delete(index, index + 2);
        }

        interfaze.addAnnotation(cacheNamespaceAnnotation.toString());

        cacheNamespaceRefAnnotation.append("(");
        for (CacheRefProperty cacheRefProperty : CacheRefProperty.values()) {
            addAttributeIfExists(interfaze, cacheNamespaceRefAnnotation, introspectedTable, cacheRefProperty);
        }
        cacheNamespaceRefAnnotation.append(")");

        index = cacheNamespaceRefAnnotation.lastIndexOf(",");
        if (index > 0) {
            cacheNamespaceRefAnnotation.delete(index, index + 2);
            interfaze.addAnnotation(cacheNamespaceRefAnnotation.toString());
            interfaze.addImportedType(cacheNamespaceRef);
        }

        return true;
    }

    /**
     * 添加{@link org.apache.ibatis.annotations.CacheNamespace}注解属性值
     *
     * @param interfaze         mapper接口
     * @param annotation        注解字串
     * @param introspectedTable 表
     * @param cacheProperty     缓存属性配置
     */
    private void addAttributeIfExists(Interface interfaze, StringBuilder annotation, IntrospectedTable introspectedTable, CacheProperty cacheProperty) {
        String property = introspectedTable.getTableConfigurationProperty(cacheProperty.getPropertyName());
        if (property == null) {
            property = properties.getProperty(cacheProperty.getPropertyName());
        }

        if (StringUtility.stringHasValue(property)) {
            switch (cacheProperty) {
                case TYPE:
                    addAttributeFroClass(interfaze, annotation, cacheProperty.getAttributeName(), property);
                    break;
                case EVICTION:
                    addAttributeFroClass(interfaze, annotation, cacheProperty.getAttributeName(), property);
                    break;
                case PROPERTIES:
                    interfaze.addImportedType(this.property);
                    addAttributeFroArray(annotation, cacheProperty.getAttributeName(), property);
                    break;
                default:
                    addAttributeFroBase(annotation, cacheProperty.getAttributeName(), property);
            }
            annotation.append(", ");
        }
    }

    /**
     * 添加{@link org.apache.ibatis.annotations.CacheNamespaceRef}注解属性值
     *
     * @param interfaze         mapper接口
     * @param annotation        注解字串
     * @param introspectedTable 表
     * @param cacheRefProperty  缓存属性配置
     */
    private void addAttributeIfExists(Interface interfaze, StringBuilder annotation, IntrospectedTable introspectedTable, CacheRefProperty cacheRefProperty) {
        String property = introspectedTable.getTableConfigurationProperty(cacheRefProperty.getPropertyName());
        if (property == null) {
            property = properties.getProperty(cacheRefProperty.getPropertyName());
        }

        if (StringUtility.stringHasValue(property)) {
            switch (cacheRefProperty) {
                case NAME:
                    addAttributeFroString(annotation, cacheRefProperty.getAttributeName(), property);
                    break;
                case VALUE:
                    addAttributeFroClass(interfaze, annotation, cacheRefProperty.getAttributeName(), property);
                    break;
                default:
                    addAttributeFroString(annotation, cacheRefProperty.getAttributeName(), property);
            }
            annotation.append(", ");
        }
    }

    /**
     * 添加字符串类型属性值
     *
     * @param annotation    注解字串
     * @param attributeName 属性名称
     * @param property      属性值
     */
    private void addAttributeFroString(StringBuilder annotation, String attributeName, String property) {
        annotation.append(attributeName);
        annotation.append(" = \"");
        annotation.append(property);
        annotation.append("\"");
    }

    /**
     * 添加基本数据类型属性值
     *
     * @param annotation    注解字串
     * @param attributeName 属性名称
     * @param property      属性值
     */
    private void addAttributeFroBase(StringBuilder annotation, String attributeName, String property) {
        annotation.append(attributeName);
        annotation.append(" = ");
        annotation.append(property);
    }

    /**
     * 添加CLass类型属性值
     *
     * @param interfaze     mapper接口
     * @param annotation    注解字串
     * @param attributeName 属性名称
     * @param property      属性值
     */
    private void addAttributeFroClass(Interface interfaze, StringBuilder annotation, String attributeName, String property) {
        FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType(property);
        interfaze.addImportedType(fullyQualifiedJavaType);
        annotation.append(attributeName);
        annotation.append(" = ");
        annotation.append(property.substring(property.lastIndexOf(".") + 1));
        annotation.append(".class");
    }

    /**
     * 添加array字串类型属性值
     *
     * @param annotation    注解字串
     * @param attributeName 属性名称
     * @param property      属性值
     */
    private void addAttributeFroArray(StringBuilder annotation, String attributeName, String property) {
        annotation.append(attributeName);
        annotation.append(" = ");
        annotation.append("{");
        property = property = property.substring(1, property.length() - 1);
        property = property.replaceAll("'", "\\\"");
        property = property.replaceAll("\\{", "\\(");
        property = property.replaceAll("}", "\\)");
        String[] split = property.split("(?<=\\)),");
        for (String s : split) {
            annotation.append("@Property");
            annotation.append(s.trim());
            annotation.append(", ");
        }
        annotation.delete(annotation.length() - 2, annotation.length());
        annotation.append("}");
    }

}
