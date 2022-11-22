package com.tu.study.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author tuyongjian
 * @date 2022/11/17 14:48
 */
@Service
public class MyListener1 implements ApplicationListener<MyEvent> {


    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        this.print();
    }

    public void print(){
        System.out.println("i am listener2 i am work");
    }
}
