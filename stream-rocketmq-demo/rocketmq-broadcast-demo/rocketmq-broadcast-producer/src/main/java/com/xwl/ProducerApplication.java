package com.xwl;

import com.xwl.dto.SimpleMsg;
import org.apache.rocketmq.common.message.MessageConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.GenericMessage;

import java.util.HashMap;
import java.util.Map;


/**
 * @author xuewl
 */
@SpringBootApplication
public class ProducerApplication {
    private static final Logger log = LoggerFactory
            .getLogger(ProducerApplication.class);
    @Autowired
    private StreamBridge streamBridge;

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Bean
    public ApplicationRunner producer() {
        return args -> {
            for (int i = 0; i < 100; i++) {
                String key = "KEY" + i;
                Map<String, Object> headers = new HashMap<>();
                headers.put(MessageConst.PROPERTY_KEYS, key);
                headers.put(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID, i);
                Message<SimpleMsg> msg = new GenericMessage<SimpleMsg>(new SimpleMsg("Hello RocketMQ " + i), headers);
                streamBridge.send("producer-out-0", msg);
            }
        };
    }
}