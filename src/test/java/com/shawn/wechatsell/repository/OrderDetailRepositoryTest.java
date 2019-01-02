package com.shawn.wechatsell.repository;

import com.shawn.wechatsell.dataObject.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: zxx
 * @Date: 2018/12/17 17:20
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailRepositoryTest {

    @Autowired
    private OrderDetailRepository repository;


    /*private String detailId;
    private String orderId;
    private String productId;
    private String productName;
    private BigDecimal productPrice;
    private Integer productQuanlity;
    private String productIcon;*/

    @Test
    public void saveTest(){
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setDetailId("1234567810");
        orderDetail.setOrderId("1111112");
        orderDetail.setProductId("1111113");
        orderDetail.setProductName("大皮蛋粥");
        orderDetail.setProductPrice(new BigDecimal(3.2));
        orderDetail.setProductQuanlity(5);
        orderDetail.setProductIcon("http://uuu.jpg");

        OrderDetail result = repository.save(orderDetail);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByOrderId() {
        List<OrderDetail> orderDetailList = repository.findByOrderId("1111111");
        Assert.assertNotEquals(0,orderDetailList.size());
    }
}