package com.shawn.wechatsell.exception;

/**
 * @Author: zxx
 * @Date: 2018/12/17 20:17
 * @Version 1.0
 */

import com.shawn.wechatsell.enums.ResultEnum;

/**
 * 异常类还需要给前端返回消息。
 * 需要Enum类
 */
public class SellException extends RuntimeException {

    private Integer code;

    /**
     * 父类中是有message这个属性的，要把message的内容传递给父类的构造方法
     * 子类为什么要传值给父类的构造方法呢？
     * @param resultEnum
     */
    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
