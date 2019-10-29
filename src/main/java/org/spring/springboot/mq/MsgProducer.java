package org.spring.springboot.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * 描述:消息生产者
 *
 * @author yanpenglei
 * @create 2017-10-16 18:28
 **/
@Component
public class MsgProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("send/{message}")
    public void send(@PathVariable String message) {
        this.kafkaTemplate.send("test", message);
    }

}