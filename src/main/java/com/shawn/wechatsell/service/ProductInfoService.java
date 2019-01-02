package com.shawn.wechatsell.service;

import com.shawn.wechatsell.dataObject.ProductInfo;
import com.shawn.wechatsell.dto.CartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @Author: zxx
 * @Date: 2018/12/13 14:09
 * @Version 1.0
 */
public interface ProductInfoService {

    Optional<ProductInfo> findById(String productId);

    /**
     * 查询所有在售的商品列表 up all
     * @return
     */
    List<ProductInfo> findUpAll();

    /**
     * 需要分页，所以要传入一个Pageable的参数
     * @return
     */
    Page<ProductInfo> findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);
    /**
     * 下单减库存，取消订单加库存
     */
    //加库存，这里不需要返回值，只需要异常就可以了
    void incStock(List<CartDTO> cartDTOList);

    //减库存
    void decStock(List<CartDTO> cartDTOList);
}
