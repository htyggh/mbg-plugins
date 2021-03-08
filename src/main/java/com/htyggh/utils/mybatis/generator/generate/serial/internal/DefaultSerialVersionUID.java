package com.htyggh.utils.mybatis.generator.generate.serial.internal;

import com.htyggh.utils.mybatis.generator.generate.serial.GeneratorSerialVersionUID;
import org.mybatis.generator.api.dom.java.InnerClass;

import java.io.Serializable;

/**
 * <p>标题：DefaultSerialVersionUID</p>
 * <p>描述：使用默认的SerialVersionUID（1L）</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2018年09月19日17时00分</p>
 * <p>版本：</p>
 */
public class DefaultSerialVersionUID implements GeneratorSerialVersionUID, Serializable {

    @Override
    public String getSerialVersionUID(InnerClass innerClass) {
        return "1L";
    }
}
