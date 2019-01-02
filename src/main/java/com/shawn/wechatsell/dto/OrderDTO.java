package com.shawn.wechatsell.dto;

import com.shawn.wechatsell.dataObject.OrderDetail;
import com.shawn.wechatsell.enums.OrderStatusEnum;
import com.shawn.wechatsell.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: zxx
 * @Date: 2018/12/17 17:49
 * @Version 1.0
 */
@Data
public class OrderDTO {

    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus;
    private Integer payStatus;

    private List<OrderDetail> orderDetailList;


}
