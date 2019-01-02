package com.shawn.wechatsell.repository;

import com.shawn.wechatsell.dataObject.OrderMaster;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * @Author: zxx
 * @Date: 2018/12/16 20:34
 * @Version 1.0
 */
public interface OrderMasterRepository extends JpaRepository<OrderMaster,String> {

    /**
     * Pageable导包的时候一定要注意区别awt和springframework
     * @param buyerOpenid
     * @param pageable
     * @return
     */
    Page<OrderMaster> findByBuyerOpenid(String buyerOpenid, Pageable pageable);
}
