package com.shawn.wechatsell.service.impl;

import com.shawn.wechatsell.dataObject.ProductInfo;
import com.shawn.wechatsell.dto.CartDTO;
import com.shawn.wechatsell.enums.ProductStatusEnum;
import com.shawn.wechatsell.enums.ResultEnum;
import com.shawn.wechatsell.exception.SellException;
import com.shawn.wechatsell.repository.ProductInfoRepository;
import com.shawn.wechatsell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Author: zxx
 * @Date: 2018/12/13 14:14
 * @Version 1.0
 */
@Service
public class ProductInfoServiceImp implements ProductInfoService {

    @Autowired
    private ProductInfoRepository repository;

    @Override
    public Optional<ProductInfo> findById(String productId) {
        return repository.findById(productId);
    }

    /**
     * 好多地方都要使用一些特定的数值，来代表一些状态，可以使用enum 枚举类型
     * 使用ProductStatusEnum.UP.getCode() 的方式来获取状态码
     * @return
     */
    @Override
    public List<ProductInfo> findUpAll() {
        return repository.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        /**
         * 如果直接使用pageable 作为参数，返回的是一个page的对象
         * 而findAll()方法需要的是一个List<ProductInfo> 的list
         * 所以需要将ProductInfoService中的findAll()的返回值类型改成Page<ProductInfo>
         */
        return repository.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return repository.save(productInfo);
    }

    @Override
    public void incStock(List<CartDTO> cartDTOList) {

        for(CartDTO cartDTO:cartDTOList){

            /**
             * 注意在后面添加get()方法。
             */
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            /**
             * 这边是否需要判断一些条件？如果商品已下架怎么处理。给退还是不给退？
             */
            Integer i = productInfo.getProductStock() + cartDTO.getProductQuanlity();
            productInfo.setProductStock(i);
            /*if(i>=0){
                productInfo.setProductStock(i);
            }else{
                throw new SellException(ResultEnum.PRODUCT_NOT_ENOUGH);
            }*/
            repository.save(productInfo);
        }
    }

    /**
     * 要么全部都成功，要么就全部不成功
     * @param cartDTOList
     */
    @Override
    @Transactional
    public void decStock(List<CartDTO> cartDTOList) {
        /**
         * 逻辑上很简单，就看库存够不够，够的话就减库存
         */
        for(CartDTO cartDTO:cartDTOList){

            /**
             * 注意在后面添加get()方法。
             */
            ProductInfo productInfo = repository.findById(cartDTO.getProductId()).get();
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            /**
             * 判断库存是否足够,当高并发的时候，可能出现超卖的情况
             * 在后面会使用redis的锁机制来避免这个问题，
             * 项目优化部分
             */
            Integer i = productInfo.getProductStock() - cartDTO.getProductQuanlity();
            if(i>=0){
                productInfo.setProductStock(i);
            }else{
                throw new SellException(ResultEnum.PRODUCT_NOT_ENOUGH);
            }
            repository.save(productInfo);
        }

    }
}
