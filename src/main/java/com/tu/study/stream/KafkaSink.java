package com.tu.study.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author tuyongjian
 * @date 2023/5/12 9:24
 */

public interface KafkaSink {

    String INPUT="kafka-input";

    @Input("kafka-input")
    SubscribableChannel input();
}
