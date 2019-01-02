package com.shawn.wechatsell.repository;

import com.shawn.wechatsell.dataObject.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

/**
 * @Author: zxx
 * @Date: 2018/12/17 15:18
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMasterRepositoryTest {

    @Autowired
    private OrderMasterRepository repository;

    @Test
    public void saveTest(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("123456");
        orderMaster.setBuyerName("王六");
        orderMaster.setBuyerPhone("12345678909");
        orderMaster.setBuyerAddress("陕西西安");
        orderMaster.setBuyerOpenid("abc1234");
        orderMaster.setOrderAmount(new BigDecimal(2.5));

        OrderMaster result = repository.save(orderMaster);
        Assert.assertNotNull(result);
    }

    @Test
    public void findByBuyerOpenidTest() {
        Pageable request = PageRequest.of(0,3);
        Page<OrderMaster> result =  repository.findByBuyerOpenid("abc1234",request);
        Assert.assertNotEquals(0,result.getTotalElements());
    }
}