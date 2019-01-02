package com.shawn.wechatsell.service.impl;

import com.shawn.wechatsell.dataObject.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: zxx
 * @Date: 2018/12/12 20:03
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl service;

    @Test
    public void findById() {
        ProductCategory productCategory = service.findById(10).get();
        Assert.assertEquals(new Integer(1),productCategory.getCategoryId());
    }


    @Test
    public void findAll() {
        List<ProductCategory> result = service.findAll();
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void findByCategoryTypeIn() {
        List<ProductCategory> result = service.findByCategoryTypeIn(Arrays.asList(1,2,3,4));
        Assert.assertNotEquals(0,result.size());
    }

    @Test
    public void save() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryType(5);
        productCategory.setCategoryName("大波浪");
        ProductCategory result = service.save(productCategory);
        Assert.assertNotNull(result);

    }
}