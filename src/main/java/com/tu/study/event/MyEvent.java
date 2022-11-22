package com.tu.study.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author tuyongjian
 * @date 2022/11/17 14:47
 */

public class MyEvent extends ApplicationEvent {

    public MyEvent(Object source) {
        super(source);
    }
}
