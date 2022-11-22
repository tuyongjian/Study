package com.tu.study.event;

import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author tuyongjian
 * @date 2022/11/17 14:56
 */
@SpringBootTest
public class Test {

    @Resource
    private MyService myService;

    @org.junit.jupiter.api.Test
    public void testEvent(){
        myService.test();
    }

}
