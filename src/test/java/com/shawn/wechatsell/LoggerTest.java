package com.shawn.wechatsell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: zxx
 * @Date: 2018/12/6 14:31
 * @Version 1.0
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
    /*
    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);
    */
    @Test
    public void test1(){
        String name = "shawn";
        String password = "123456";
        /*
        两种方法拼接字符串：
        1、使用“”+“”的方式去拼接："name:"+name+"password:"+password
        ※2、使用占位符{ }拼接："name:{},password{}",name,password，推荐采用第二种方式
         */
        log.debug("debug.....");
        /* 系统默认的日志输出是info，在info之上的可以输出，之下的无法输出*/
        log.info("info ......");
        log.error("error.....");
        log.warn("warn ....");
    }
}
