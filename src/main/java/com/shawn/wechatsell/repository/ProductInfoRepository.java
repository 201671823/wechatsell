package com.shawn.wechatsell.repository;

import com.shawn.wechatsell.dataObject.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * @Author: zxx
 * @Date: 2018/12/13 11:02
 * @Version 1.0
 */
public interface ProductInfoRepository extends JpaRepository<ProductInfo,String> {

    List<ProductInfo> findByProductStatus(Integer productStatus);

//    ProductInfo findById(String productId);

//    Optional<ProductInfo> findOne(String productId);
}
