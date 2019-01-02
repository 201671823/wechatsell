package com.shawn.wechatsell.service.impl;

import com.shawn.wechatsell.dataObject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 *
 *
 * @Author: zxx
 * @Date: 2018/12/13 15:41
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoServiceImpTest {

    @Autowired
    private ProductInfoServiceImp service;

    @Test
    public void findById() {

        Optional<ProductInfo> productInfo = service.findById("12345");
//        Assert.assertEquals(12345,productInfo);
        Assert.assertNotNull(productInfo);

    }

    @Test
    public void findUpAll() {
        List<ProductInfo> productInfoList = service.findUpAll();
        Assert.assertNotEquals(0,productInfoList.size());
   }

    @Test
    public void findAll() {
        PageRequest pageRequest = PageRequest.of(0,10);
        Page<ProductInfo> productInfoPage = service.findAll(pageRequest);
        System.out.println(productInfoPage.getTotalElements());
        Assert.assertNotNull(productInfoPage);
    }

    @Test
    public void save() {
        ProductInfo productInfo = new ProductInfo("123457","牛肉瘦肉粥",
                new BigDecimal(5.2),50,"真的很好喝的粥",
                "http://yyy.jpg",0,5);
        ProductInfo result = service.save(productInfo);
        Assert.assertNotNull(result);
    }
}