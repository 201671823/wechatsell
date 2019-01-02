package com.shawn.wechatsell.enums;

import lombok.Getter;

/**
 * @Author: zxx
 * @Date: 2018/12/17 20:19
 * @Version 1.0
 * 返回给前端的消息,ctrl+shift+u变成大写
 */
@Getter
public enum ResultEnum {

    PRODUCT_NOT_EXIST(10,"商品不存在"),
    PRODUCT_NOT_ENOUGH(11,"商品数量不足"),
    ORDER_NOT_EXSIT(12,"订单不存在"),
    ORDERDETAIL_NOT_EXSIT(13,"订单详情不存在"),
    ORDERSTATUS_NOT_RIGHT(14,"订单状态不正确"),
    ORDER_UPDATE_FAIL(15,"订单修改失败"),
    ORDERDETAIL_NOT_EMPTY(16,"订单详情为空")
    ;

    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
