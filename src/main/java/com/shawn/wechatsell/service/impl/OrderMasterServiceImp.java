package com.shawn.wechatsell.service.impl;

import com.shawn.wechatsell.converter.OrderMaster2OrderDTO;
import com.shawn.wechatsell.dataObject.OrderDetail;
import com.shawn.wechatsell.dataObject.OrderMaster;
import com.shawn.wechatsell.dataObject.ProductInfo;
import com.shawn.wechatsell.dto.CartDTO;
import com.shawn.wechatsell.dto.OrderDTO;
import com.shawn.wechatsell.enums.OrderStatusEnum;
import com.shawn.wechatsell.enums.PayStatusEnum;
import com.shawn.wechatsell.enums.ResultEnum;
import com.shawn.wechatsell.exception.SellException;
import com.shawn.wechatsell.repository.OrderDetailRepository;
import com.shawn.wechatsell.repository.OrderMasterRepository;
import com.shawn.wechatsell.service.OrderMasterService;
import com.shawn.wechatsell.service.ProductInfoService;
import com.shawn.wechatsell.util.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author: zxx
 * @Date: 2018/12/17 19:28
 * @Version 1.0
 */
@Slf4j
@Service
public class OrderMasterServiceImp implements OrderMasterService{
    /**
     * 类似于这样的变量为什么偏偏使用repository接口
     */
    @Autowired
    private OrderMasterRepository masterRepository;

    @Autowired
    private OrderDetailRepository detailRepository;

    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 创建订单就是直接写入数据库么？当然不是
     * 入参就是OrderDTO，是通过Controller层传过来的，但是Controller层
     * 不可能把所有的OrderDTO的参数都传过来。比如：订单总金额是需要经过计算的，
     * 订单明细也不会是全部传入的。
     * 尤其是单价，单价绝对不能是从前端传入的，价格从数据库中去取。
     * @param orderDTO
     * @return
     *
     * 注意 org.springframework.transaction.annotation.Transactional;
     * 异常的情况下，事务就会回滚
     */
    @Override
    @Transactional
    public OrderDTO create(OrderDTO orderDTO) {
        /** 生成一个随机的订单编号 */
        String orderId = KeyUtil.getUniqueKey();
        /** 0 也可以写成 BigInteger.ZERO */
        BigDecimal orderAmount = new BigDecimal(0);
        ProductInfo productInfo;

//        List<CartDTO> cartDTOList = new ArrayList<>();

        //1、查询商品（数量、价格）-从OrderDTO中的List<OrderDetail>中查询。
        for(OrderDetail orderDetail : orderDTO.getOrderDetailList()){
            productInfo = productInfoService.findById(orderDetail.getProductId()).get();
            /** 如果商品不存在 */
            if(productInfo == null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            /** 判断数量是否够，但是下面有第4步扣库存的时候要进行库存判断，所以这个地方不做，留到第4步做 */

            //2、计算订单总价,
            // BigDecimal类型是无法通过 * 做乘法,无法通过 + 做加法
            //orderDetail.getProductPrice()*orderDetail.setProductQuanlity();
            //注意获取价格的地方不是orderDetail中，而是productInfo中
            orderAmount = productInfo.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuanlity()))
                .add(orderAmount);

            //3、订单详情入库
            //3.1 传入两个随机数字,用作测试使用，后面是要取消的
            orderDetail.setDetailId(KeyUtil.getUniqueKey());
            orderDetail.setOrderId(orderId);
            //在设置了两个API中约定的传入值之后，还需要通过数据库查询出来的的productInfo数据
            //对orderDetail对象进行属性值的设置，但是那种设置方式不友好。可以使用BeanUtils的
            //copyProperties();进行对象属性值的拷贝。将productInfo中的值拷贝到orderDetail里面
            BeanUtils.copyProperties(productInfo,orderDetail);
            //订单详情入库了，但是好像还是有点什么问题。
            detailRepository.save(orderDetail);
            /**
             * 有污染旧代码的嫌疑
             */
            /*CartDTO cartDTO = new CartDTO(orderDetail.getProductId(),orderDetail.getProductQuanlity());
            cartDTOList.add(cartDTO);*/
        }

        //2、计算总价

        //3、写入订单数据库（OrderMaster和OrderDetail）
        //构造订单
        OrderMaster orderMaster = new OrderMaster();
       //先拷贝，后设置
        BeanUtils.copyProperties(orderDTO,orderMaster);

        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(orderAmount);

        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        masterRepository.save(orderMaster);

        //4、下单成功，需要扣库存，库存需要从orderDTO.getOrderDetailList()中来。
        /**
         * 当然可以在遍历的时候创建CartDTO对象，并逐个add到CartDTO的ArrayList中去。
         */

        List<CartDTO> cartDTOS = new ArrayList<>();
        cartDTOS = orderDTO.getOrderDetailList().stream().map(e ->
        new CartDTO(e.getProductId(),e.getProductQuanlity())
        ).collect(Collectors.toList());

        productInfoService.decStock(cartDTOS);

        return orderDTO;
    }

    @Override
    public OrderDTO findByOrderId(String orderId) {
        OrderMaster orderMaster = masterRepository.findById(orderId).get();
        if(orderMaster == null){
            throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
        }

        List<OrderDetail> orderDetailList = detailRepository.findByOrderId(orderId);

        if(CollectionUtils.isEmpty(orderDetailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXSIT);
        }

        OrderDTO orderDTO = new OrderDTO();
        BeanUtils.copyProperties(orderMaster,orderDTO);
        orderDTO.setOrderDetailList(orderDetailList);
        return orderDTO;
    }

    @Override
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = masterRepository.findByBuyerOpenid(buyerOpenid,pageable);
        //前端，显示具体的列表的时候是不显示详情的
        //查询到的是OrderMaster需要的是OrderDTO，所以还要进行类型的转换
        //空的列表 不需要像之前一样 使用异常来处理，没有东西本身就很正常
        List<OrderDTO> orderDTOList = OrderMaster2OrderDTO
                .convert(orderMasterPage.getContent());

        Page<OrderDTO> orderDTOPage = new PageImpl<OrderDTO>(orderDTOList,
                pageable,orderMasterPage.getTotalElements());
        return orderDTOPage;
    }

    @Override
    public OrderDTO cancel(OrderDTO orderDTO) {
        OrderMaster orderMaster = new OrderMaster();
        //判断订单状态
        if(!orderDTO.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())) {
//            log.error("【取消订单】订单状态不正确，orderId={}，orderStatus",orderDTO.getOrderId(),orderDTO.getOrderStatus());
            throw new SellException(ResultEnum.ORDERSTATUS_NOT_RIGHT);
        }
        //修改订单状态
        orderDTO.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDTO,orderMaster);
        OrderMaster updateResult =  masterRepository.save(orderMaster);
        if(updateResult == null) {
//            log.error("【取消订单】更新失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderDTO.getOrderDetailList())) {
//            log.error("【取消订单】订单中无商品详情，orderDTO={}",orderDTO);
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EMPTY);
        }
        List<CartDTO> cartDTOList = orderDTO.getOrderDetailList().stream()
                .map(e -> new CartDTO(e.getProductId(),e.getProductQuanlity()))
                .collect(Collectors.toList());
        productInfoService.incStock(cartDTOList);
        //如果已支付，需要退款
        if(orderDTO.getPayStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            //TODO
        }
        return orderDTO;

    }

    @Override
    public OrderDTO finish(OrderDTO orderDTO) {
        return null;
    }

    @Override
    public OrderDTO paid(OrderDTO orderDTO) {
        return null;
    }
}
