package com.shawn.wechatsell.repository;

import com.shawn.wechatsell.dataObject.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: zxx
 * @Date: 2018/12/16 20:47
 * @Version 1.0
 */
public interface OrderDetailRepository extends JpaRepository<OrderDetail,String> {

    /**
     * master表中的一条记录可能对应detail表中的多条记录
     * 所以这个地方返回的是一个list
     */
    List<OrderDetail> findByOrderId(String orderId);
}
