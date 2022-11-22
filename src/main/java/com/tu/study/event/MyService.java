package com.tu.study.event;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author tuyongjian
 * @date 2022/11/17 14:53
 */
@Service
public class MyService {

    @Resource
    private ApplicationContext applicationContext;

    public void test(){
        System.out.println("i am test method");

        new Thread(){
            @Override
            public void run() {
                applicationContext.publishEvent(new MyEvent(this) );
            }
        }.start();

    }
}
