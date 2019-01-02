package com.shawn.wechatsell.enums;

import lombok.Getter;

/**
 * @Author: zxx
 * @Date: 2018/12/17 15:02
 * @Version 1.0
 */
@Getter
public enum PayStatusEnum {
    WAIT(0,"等待支付"),
    SUCCESS(1,"支付成功");

    private Integer code;
    private String message;

    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
