package com.htyggh.utils.mybatis.generator.generate.serial.internal;

import com.htyggh.utils.mybatis.generator.generate.serial.GeneratorSerialVersionUID;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.InnerClass;

import java.util.List;

/**
 * <p>标题：JVMSerialVersionUID</p>
 * <p>描述：使用jvm提供的算法计算SerialVersionUID</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2018年09月19日16时53分</p>
 * <p>版本：</p>
 */
public class JVMSerialVersionUID implements GeneratorSerialVersionUID {

    @Override
    public String getSerialVersionUID(InnerClass innerClass) {
        List<Field> fields = innerClass.getFields();

        return null;
    }
}
