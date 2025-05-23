package com.xwl;


import com.xwl.dto.SimpleMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.Message;

import java.util.function.Consumer;

/**
 * @author xuewl
 */
@SpringBootApplication
public class Consumer1Application {
    private static final Logger log = LoggerFactory
            .getLogger(Consumer1Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Consumer1Application.class, args);
    }

    @Bean
    public Consumer<Message<SimpleMsg>> consumer() {
        return msg -> {
            log.info(Thread.currentThread().getName() + " Consumer1 Receive New Messages: " + msg.getPayload().getMsg());
        };
    }
}
