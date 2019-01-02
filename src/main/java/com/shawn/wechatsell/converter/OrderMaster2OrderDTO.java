package com.shawn.wechatsell.converter;

import com.shawn.wechatsell.dataObject.OrderMaster;
import com.shawn.wechatsell.dto.OrderDTO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: zxx
 * @Date: 2019/1/2 15:12
 * @Version 1.0
 */
public class OrderMaster2OrderDTO {

    public static OrderDTO convert(OrderMaster orderMaster){
        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        return orderDTO;
    }

    public static List<OrderDTO> convert(List<OrderMaster> orderMasterList){

        return orderMasterList.stream().map(e->
                convert(e)
        ).collect(Collectors.toList());
    }


    /*public static List<OrderDTO> convert1(List<OrderMaster> orderMasterList){
        List<OrderDTO> orderDTOList = new ArrayList<>();
        if(orderMasterList == null){
            return null;
        }
        for(OrderMaster orderMaster : orderMasterList){
            orderDTOList.add(OrderMaster2OrderDTO.convert(orderMaster));
        }
        return orderDTOList;
    }*/
}
