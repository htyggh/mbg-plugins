package com.htyggh.utils.mybatis.generator.generate.serial.internal;

import com.htyggh.utils.mybatis.generator.generate.serial.GeneratorSerialVersionUID;
import org.mybatis.generator.api.dom.java.InnerClass;

/**
 * <p>标题：AutoIncrementSerialVersionUID</p>
 * <p>描述：</p>
 * <p>公司：</p>
 * <p>创建人：htyggh</p>
 * <p>创建时间：2019年05月06日12时43分</p>
 * <p>版本：</p>
 */
public class AutoIncrementSerialVersionUID implements GeneratorSerialVersionUID {

    private long serialVersionUID = 0L;

    @Override
    public String getSerialVersionUID(InnerClass innerClass) {
        return ++serialVersionUID + "L";
    }

}
