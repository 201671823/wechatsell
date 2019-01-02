package com.shawn.wechatsell.service;

import com.shawn.wechatsell.dataObject.ProductCategory;

import java.util.List;
import java.util.Optional;

/**
 * @Author: zxx
 * @Date: 2018/12/12 19:12
 * @Version 1.0
 */
public interface ProductCategoryService {

    Optional<ProductCategory> findById(Integer categoryId);
    //List<ProductCategory> findByCategoryType(Integer categoryType);
    List<ProductCategory> findAll();
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType);
    ProductCategory save(ProductCategory productCategory);
}
