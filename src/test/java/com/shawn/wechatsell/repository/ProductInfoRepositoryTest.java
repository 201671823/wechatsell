package com.shawn.wechatsell.repository;

import com.shawn.wechatsell.dataObject.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: zxx
 * @Date: 2018/12/13 11:10
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductInfoRepositoryTest {

    @Autowired
    private ProductInfoRepository repository;

    @Test
    public void saveTest(){
        ProductInfo productInfo = new ProductInfo("123456","皮蛋瘦肉粥",
                new BigDecimal(3.2),100,"很好喝的粥",
                "http://xxx.jpg",0,2);
        ProductInfo result = repository.save(productInfo);
//        Assert.assertNotEquals(null,result);
        Assert.assertNotNull(result);
    }


    @Test
    public void findByProductStatusTest() {
        List<ProductInfo> productInfoList = repository.findByProductStatus(0);
        Assert.assertNotEquals(0,productInfoList.size());
    }
}