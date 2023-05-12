package com.tu.study.stream;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

/**
 * @author tuyongjian
 * @date 2023/5/11 16:44
 */
@Service
@EnableBinding(KafkaSink.class)
public class KafkaMessageSinkHandler {

    @StreamListener(KafkaSink.INPUT)
    public void hander(Message<String> msg){
        System.out.println("-------kafka--------"+msg);
    }
}
