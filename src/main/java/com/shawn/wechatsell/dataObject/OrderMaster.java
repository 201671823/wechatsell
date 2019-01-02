package com.shawn.wechatsell.dataObject;

import com.shawn.wechatsell.enums.OrderStatusEnum;
import com.shawn.wechatsell.enums.PayStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @Author: zxx
 * @Date: 2018/12/16 20:31
 * @Version 1.0
 */
@Entity
@Data
public class OrderMaster {
    @Id
    private String orderId;
    private String buyerName;
    private String buyerPhone;
    private String buyerAddress;
    private String buyerOpenid;
    private BigDecimal orderAmount;
    private Integer orderStatus = OrderStatusEnum.NEW.getCode();
    private Integer payStatus = PayStatusEnum.WAIT.getCode();

    /**
     * 添加一个orderDetail属性,
     * 添加了属性之后，数据库对应的时候会出现问题，那么可以添加@Transient进行属性对应忽略
     * 但是这样也不好，破坏了dataObject的规则，该类就是给数据库对象使用的。
     * 所以我们使用dto，Data Transfer Objec 数据传输对象
     * @return
     * @Transient
        private List<OrderDetail> orderDetailList;
     */


    @Override
    public String toString() {
        return "OrderMasterEntity{" +
                "orderId='" + orderId + '\'' +
                ", buyerName='" + buyerName + '\'' +
                ", buyerPhone='" + buyerPhone + '\'' +
                ", buyerAddress='" + buyerAddress + '\'' +
                ", buyerOpenid='" + buyerOpenid + '\'' +
                ", orderAmount=" + orderAmount +
                '}';
    }
}
