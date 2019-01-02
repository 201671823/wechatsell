package com.shawn.wechatsell.util;

import java.util.Random;

/**
 * @Author: zxx
 * @Date: 2018/12/17 21:04
 * @Version 1.0
 */
public class KeyUtil {
    /**
     * 生成唯一主键
     * 格式：时间+随机数
     * 多线程并发的时候还是可能会重复的，所以加上synchronized关键字
     * @return
     */

    public static synchronized String getUniqueKey(){
        Random random = new Random();
        Integer a = random.nextInt(900000)+100000;
        return System.currentTimeMillis() + String.valueOf(a);
    }
}
