package com.shawn.wechatsell.util;

import com.shawn.wechatsell.ViewObject.ResultVO;

/**
 * @Author: zxx
 * @Date: 2018/12/14 9:39
 * @Version 1.0
 */
public class ResultVOUtil {
    /**
     * 这个组件类建立之后，在返回给前端的Controller中就可以直接使用了
     * @param object
     * @return
     */

    /**
     * 成功
     * @param object
     * @return
     */
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMessage("成功");
        return resultVO;
    }

    /**
     * 成功，返回空
     */
    public static ResultVO success(){
        return null;
    }

    /**
     * 失败,返回错误代码和信息
     */

    public static ResultVO error(Integer code,String message){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        return resultVO;
    }
}
