package org.spring.springboot.mq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;



@Component
public class MsgConsumer {

    @KafkaListener(groupId = "simpleGroup",topics = { "fddffffff" })
    public void consumer(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("消息消费--》" + consumerRecord.value());
    }
}
