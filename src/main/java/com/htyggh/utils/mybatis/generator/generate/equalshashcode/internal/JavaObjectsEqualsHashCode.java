package com.htyggh.utils.mybatis.generator.generate.equalshashcode.internal;

import com.htyggh.utils.mybatis.generator.generate.equalshashcode.GeneratorEqualsHashCode;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.api.dom.java.JavaVisibility;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.Parameter;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.util.*;

import static java.lang.Character.toLowerCase;
import static org.mybatis.generator.api.dom.OutputUtilities.javaIndent;

/**
 * <p>标题：JavaObjectsEqualsHashCode</p>
 * <p>描述：生成基于JDK1.7及以上版本{@link Object}类的{@code Equals}和{@code HashCode}方法</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年05月23日16时19分</p>
 * <p>版本：</p>
 */
public class JavaObjectsEqualsHashCode implements GeneratorEqualsHashCode {

    @Override
    public Method generateEquals(TopLevelClass topLevelClass, List<IntrospectedColumn> introspectedColumns, IntrospectedTable introspectedTable) {

        Method method = new Method("equals");
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setReturnType(FullyQualifiedJavaType
                .getBooleanPrimitiveInstance());
        method.setName("equals");
        method.addParameter(new Parameter(FullyQualifiedJavaType
                .getObjectInstance(), "o"));

        method.addBodyLine("if (this == o) return true;");
        method.addBodyLine("if (o == null || getClass() != o.getClass()) return false;");

        if (topLevelClass.getSuperClass().isPresent()) {
            method.addBodyLine("if (!super.equals(o)) return false;");
        }

        String type = topLevelClass.getType().getShortName();
        String alias = toLowerCase(type.charAt(0)) + type.substring(1);

        boolean array = false;
        boolean object = false;

        StringBuilder sb = new StringBuilder();
        sb.append(type);
        sb.append(" ");
        sb.append(alias);
        sb.append(" = (");
        sb.append(type);
        sb.append(") o;");
        method.addBodyLine(sb.toString());

        boolean first = true;
        Iterator<IntrospectedColumn> iter = introspectedColumns.iterator();
        while (iter.hasNext()) {
            IntrospectedColumn introspectedColumn = iter.next();

            sb.setLength(0);

            if (first) {
                sb.append("return (");
                first = false;
            } else {
                javaIndent(sb, 2);
            }

            /*String getterMethod = getGetterMethodName(
                    introspectedColumn.getJavaProperty(), introspectedColumn
                            .getFullyQualifiedJavaType());*/

            if (introspectedColumn.getFullyQualifiedJavaType().isPrimitive()) {
                sb.append(introspectedColumn.getJavaProperty());
                sb.append(" == ");
                sb.append(alias);
                sb.append(".");
                sb.append(introspectedColumn.getJavaProperty());
            } else if (introspectedColumn.getFullyQualifiedJavaType().isArray()) {
                if (!array) {
                    array = true;
                }
                sb.append("Arrays.equals(");
                sb.append(introspectedColumn.getJavaProperty());
                sb.append(", ");
                sb.append(alias);
                sb.append(".");
                sb.append(introspectedColumn.getJavaProperty());
                sb.append(")");
            } else {
                if (!object) {
                    object = true;
                }
                sb.append("Objects.equals(");
                sb.append(introspectedColumn.getJavaProperty());
                sb.append(", ");
                sb.append(alias);
                sb.append(".");
                sb.append(introspectedColumn.getJavaProperty());
                sb.append(")");
            }

            if (iter.hasNext()) {
                sb.append(" &&");
            } else {
                sb.append(");");
            }

            method.addBodyLine(sb.toString());
        }

        if (array) {
            topLevelClass.addImportedType("java.utils.Arrays");
        }

        if (object) {
            topLevelClass.addImportedType("java.utils.Objects");
        }

        return method;
    }

    @Override
    public Method generateHashCode(TopLevelClass topLevelClass, List<IntrospectedColumn> introspectedColumns, IntrospectedTable introspectedTable) {

        return null;
    }
}
