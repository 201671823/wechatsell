package com.shawn.wechatsell.repository;

/**
 * @Author: zxx
 * @Date: 2018/12/7 16:23
 * @Version 1.0
 */

import com.shawn.wechatsell.dataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * interface 继承JpaRepository
 * 添加上 <ProductRepository,Integer>
 * 测试方法：
 * 选中这个接口 ProductCategoryRepository 然后 goto test
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory,Integer> {
/**
 * 查询商品和类目，肯定是希望一次性查询到，而不是分很多次，而且通过CategoryType来查询的，而我们肯定希望是通过
 * 商品的编号来查询，
 * 也就是说，我们要通过categoryType的list来查询类目
 */
    /**
     * 无须sql语句和多余的代码
     * @param categoryTypeList
     * @return
     */
    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

}



