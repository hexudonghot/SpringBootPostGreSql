package org.spring.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class KafkaManageController {

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    @RequestMapping("kafka/stop")
    public void stop() {
        Set<String> set = kafkaListenerEndpointRegistry.getListenerContainerIds();
        for (String str : set) {
            System.out.println(str);
            MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer(str);
            listenerContainer.stop();
        }
    }

    @RequestMapping("/start")
    public void start() {
        MessageListenerContainer listenerContainer = kafkaListenerEndpointRegistry.getListenerContainer("full-part-id");
        listenerContainer.start();
    }
}
