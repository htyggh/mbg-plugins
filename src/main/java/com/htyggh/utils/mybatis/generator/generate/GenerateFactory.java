package com.htyggh.utils.mybatis.generator.generate;

import com.htyggh.utils.mybatis.generator.generate.comment.GeneratorComment;
import com.htyggh.utils.mybatis.generator.generate.equalshashcode.GeneratorEqualsHashCode;
import com.htyggh.utils.mybatis.generator.generate.serial.GeneratorSerialVersionUID;

import static org.mybatis.generator.internal.ObjectFactory.createInternalObject;

/**
 * <p>标题：GenerateFactory</p>
 * <p>描述：生成器工厂</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年05月17日13时04分</p>
 * <p>版本：</p>
 */
public final class GenerateFactory {

    /**
     * 获取注释模版工厂
     *
     * @param type 注释模版工厂实现类
     * @return 注释模版工厂
     */
    public static GeneratorComment getGeneratorComment(String type) {
        return (GeneratorComment) createInternalObject(type);
    }

    /**
     * 获取序列号生成工厂
     *
     * @param type 序列号生成工厂实现类
     * @return 序列号生成工厂
     */
    public static GeneratorSerialVersionUID getGeneratorSerialVersionUID(String type) {
        return (GeneratorSerialVersionUID) createInternalObject(type);
    }

    /**
     * 获取EqualsHashCode方法生成工厂
     *
     * @param type EqualsHashCode方法生成工厂实现类
     * @return EqualsHashCode方法生成工厂
     */
    public static GeneratorEqualsHashCode getGeneratorEqualsHashCode(String type) {
        return (GeneratorEqualsHashCode) createInternalObject(type);
    }

}
