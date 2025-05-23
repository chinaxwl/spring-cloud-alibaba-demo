package com.xwl;

import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageConst;
import org.apache.rocketmq.common.message.MessageQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author xuewl
 */
@Component
public class OrderlyMessageQueueSelector implements MessageQueueSelector {
    private static final Logger log = LoggerFactory
            .getLogger(OrderlyMessageQueueSelector.class);

    /**
     * to select a fixed queue by id.
     *
     * @param mqs all message queues of this topic.
     * @param msg mq message.
     * @param arg mq arguments.
     * @return message queue selected.
     */
    @Override
    public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
        Integer id = (Integer) ((MessageHeaders) arg).get(MessageConst.PROPERTY_ORIGIN_MESSAGE_ID);
        int index = id % RocketMQOrderlyConsumeApplication.tags.length % mqs.size();
        return mqs.get(index);
    }
}
