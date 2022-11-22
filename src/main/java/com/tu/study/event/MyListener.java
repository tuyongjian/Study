package com.tu.study.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @author tuyongjian
 * @date 2022/11/17 14:48
 */
@Service
public class MyListener{


    @EventListener(MyEvent.class)
    public void print(){
        System.out.println("i am listener1 i am work");
    }
}
