package com.shawn.wechatsell.enums;

import lombok.Getter;

/**
 * @Author: zxx
 * @Date: 2018/12/13 15:14
 * @Version 1.0
 */
@Getter
public enum ProductStatusEnum {
    UP(0,"在架"),
    DOWN(1,"下架")
    ;
    /**
     * 定义一个code变量
     */
    private Integer code;
    private String message;

    /**
     * 构造方法
     * @param code
     */
    ProductStatusEnum(Integer code,String message) {
        this.code = code;
        this.message = message;
    }
}
