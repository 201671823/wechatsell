package com.shawn.wechatsell.ViewObject;

/**
 * @Author: zxx
 * @Date: 2018/12/13 17:35
 * @Version 1.0
 */

import lombok.Data;

/**
 * Http请求返回的最外层对象
 */
@Data
public class ResultVO<T> {
    /**
     * code 错误码
     * 0代表成功
     */
    private Integer code;
    /**
     * message 提示信息
     */
    private String message;
    /**
     *data 里面是一个对象，可以定义为一个泛型
     * data 具体内容
     */
    /**
     * 这里data 也需要一个对象列表，也就是List<ProductVO>
     */
    private T data;

}
