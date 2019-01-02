package com.shawn.wechatsell.service;

import com.shawn.wechatsell.dataObject.OrderMaster;
import com.shawn.wechatsell.dto.OrderDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;

/**
 * @Author: zxx
 * @Date: 2018/12/17 17:37
 * @Version 1.0
 */
public interface OrderService {

    /** 创建订单
     * 因为一个订单中可以由多个商品，所以在OrderMaster中需要添加一个属性
     * orderDetail
     * 所以这个地方的返回类型就会改成OrderDTO对象，里面多了一个OrderDetail的list
     * OrderMaster create(OrderMaster orderMaster);
     * */
    OrderDTO create(OrderDTO orderDTO);

    /** 查询单个订单
     * 同样这个地方也是OrderDTO的对象
     * */

    OrderDTO findByOrderId(String orderId);

    /** 查询订单列表 */
    Page<OrderDTO> findAll(String buyerOpenid, Pageable pageable);

    /** 取消订单 */
    OrderDTO cancel(OrderDTO orderDTO);

    /** 完结订单 */
    OrderDTO finish(OrderDTO orderDTO);

    /** 支付订单 */
    OrderDTO paid(OrderDTO orderDTO);

}
