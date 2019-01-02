package com.shawn.wechatsell.service.impl;

import com.shawn.wechatsell.dataObject.ProductCategory;
import com.shawn.wechatsell.repository.ProductCategoryRepository;
import com.shawn.wechatsell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @Author: zxx
 * @Date: 2018/12/12 19:38
 * @Version 1.0
 */

/**
 * service 需要添加一个注解 @service
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService{


    @Autowired //引入dao，也就是repository
    private ProductCategoryRepository repository;

    @Override
    public Optional<ProductCategory> findById(Integer categoryId) {
        return repository.findById(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return repository.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryType) {
        return repository.findByCategoryTypeIn(categoryType);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return repository.save(productCategory);
    }
}

