package org.spring.springboot.mq;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


/**
 * 描述:消息消费者
 *
 * @author yanpenglei
 * @create 2017-10-16 18:33
 **/
//@Service
//class MsgConsumer {
//
//    @KafkaListener(topics = {"fddffffff"})
//    public void listen(String content) {
//        System.out.println("消息被消费"+content);
//    }
//
//}


@Component
public class MsgConsumer {

    @KafkaListener(groupId = "simpleGroup",topics = { "fddffffff" })
    public void consumer(ConsumerRecord<String, String> consumerRecord) {
        System.out.println("消息消费--》" + consumerRecord.value());
    }
}
