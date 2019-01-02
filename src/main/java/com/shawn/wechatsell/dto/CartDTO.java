package com.shawn.wechatsell.dto;

import lombok.Data;

/**
 * 购物车
 * @Author: zxx
 * @Date: 2019/1/2 9:21
 * @Version 1.0
 */
@Data
public class CartDTO {

    /** 商品id */
    private String productId;

    /** 商品数量 */
    private Integer productQuanlity;

    public CartDTO(String productId, Integer productQuanlity) {
        this.productId = productId;
        this.productQuanlity = productQuanlity;
    }
}
