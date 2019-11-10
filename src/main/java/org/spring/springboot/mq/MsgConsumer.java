package org.spring.springboot.mq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.springboot.service.impl.UserServiceImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;



@Component
public class MsgConsumer {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @KafkaListener(groupId = "simpleGroup",topics = { "fddffffff" })
    public void consumer(ConsumerRecord<String, String> consumerRecord) {
       // logger.info("消息消费--》" + consumerRecord.value());
    }
}
