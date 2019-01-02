package com.shawn.wechatsell.service.impl;

import com.shawn.wechatsell.dataObject.OrderDetail;
import com.shawn.wechatsell.dataObject.OrderMaster;
import com.shawn.wechatsell.dto.OrderDTO;
import com.shawn.wechatsell.enums.OrderStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: zxx
 * @Date: 2019/1/2 10:58
 * @Version 1.0
 */

/**
 * @Slf4j 使用日志打印
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class OrderMasterServiceImpTest {

    private final String BUYER_OPENID = "1101110";
    private final String ORDER_ID = "1546409250144122198";

    @Autowired
    private OrderMasterServiceImp orderMasterServiceImp;
    @Test
    public void create() {
        /**
         * 构造OrderDTO的对象，可以把一些公用的属性定义到类中，比如 buyerOpenid;
         */
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setBuyerAddress("西安雁塔");
        orderDTO.setBuyerName("shawn");
        orderDTO.setBuyerOpenid(BUYER_OPENID);
        orderDTO.setBuyerPhone("13000000000");
        orderDTO.setOrderStatus(0);
        //购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
            //先写一件东西
            OrderDetail o1 = new OrderDetail();
            o1.setProductId("123456");
            o1.setProductQuanlity(1);
            orderDetailList.add(o1);

            //再写一件
            OrderDetail o2 = new OrderDetail();
            o2.setProductId("123457");
            o2.setProductQuanlity(2);
            orderDetailList.add(o2);

            //第三件
            OrderDetail o3 = new OrderDetail();
            o3.setProductId("123458");
            o3.setProductQuanlity(3);
            orderDetailList.add(o3);




        orderDTO.setOrderDetailList(orderDetailList);
        OrderDTO result = orderMasterServiceImp.create(orderDTO);
        Assert.assertNotNull(result);
        log.info("[创建订单] result ={}",result);
    }

    @Test
    public void findByOrderId() {

        OrderDTO result = orderMasterServiceImp.findByOrderId(ORDER_ID);
        log.debug("[查询单个订单] reslut = {}",result);
        Assert.assertEquals(ORDER_ID,result.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest pageRequest =  PageRequest.of(0,2);
        Page<OrderDTO> orderDTOPage = orderMasterServiceImp.findList(BUYER_OPENID,pageRequest);
        Assert.assertNotNull(orderDTOPage);
    }

    @Test
    public void cancel() {
        OrderDTO orderDTO = orderMasterServiceImp.findByOrderId(ORDER_ID);
        OrderDTO result = orderMasterServiceImp.cancel(orderDTO);
        Assert.assertEquals(OrderStatusEnum.CANCEL.getCode(),result.getOrderStatus());
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}