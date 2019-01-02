package com.shawn.wechatsell.dataObject;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * @Author: zxx
 * @Date: 2018/12/16 20:42
 * @Version 1.0
 */
@Entity
@Data
public class OrderDetail {
    @Id
    private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuanlity;
    private String productIcon;
/*    private Timestamp createTime;
    private Timestamp updateTime;*/

    @Override
    public String toString() {
        return "OrderDetail{" +
                "detailId='" + detailId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", productQuanlity=" + productQuanlity +
                ", productIcon='" + productIcon + '\'' +
                '}';
    }
}
