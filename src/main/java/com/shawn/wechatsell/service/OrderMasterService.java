package com.shawn.wechatsell.service;

import com.shawn.wechatsell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @Author: zxx
 * @Date: 2018/12/17 14:51
 * @Version 1.0
 */
public interface OrderMasterService {
    /**
     * 创建订单：在OrderDTO之后，我们使用的OrderDTO
     * 对象和对象之间涉及到一个转化的问题，
     */
    OrderDTO create(OrderDTO orderDTO);
    /**
     * 查询单个订单
     */
    OrderDTO findByOrderId(String orderId);

    /**
     * 查询订单列表
     */

    Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);
    /**
     * 取消订单
     */
    OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     */
    OrderDTO finish(OrderDTO orderDTO);
    /**
     * 支付订单
     */
    OrderDTO paid(OrderDTO orderDTO);
}
