package com.shawn.wechatsell.ViewObject;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: zxx
 * @Date: 2018/12/13 17:55
 * @Version 1.0
 */

/**
 * 商品包含类目
 */
@Data
public class ProductVO {

    /**
     * 对于很多的类都有可能重名属性，届时将会变得不好理解和辨析
     * 在对象类当中可以定义的详细一些，然后加上
     * @JsonProperty("name")，
     * 将来返回的时候就会是name
     */
    @JsonProperty("name")
    private String categoryName;
    /**
     * 同样的注解可以给type应用
     */
    @JsonProperty("type")
    private Integer categoryType;

    /**
     * food
     * 包含了id name price description icon
     * 那么需要将其定义为一个对象。
     * 有两种选择：1、使用dataObject中的对象  2、重新定义一个对象
     * 很大的原因上是因为安全和隐私，避免其他的字段暴露，同时根据供求想对应的原则，重新定义一个对象更加合适。
     * 比如：库存，库存是不需要被前端访问的，如果你返回了那么库存值就暴露了，被竞争对手发现就麻烦了^_^
     */
    /**
     * 这里返回的对象类型是list<ProductInfoVO>
     */
    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOList;

}
