//package org.spring.springboot.mq;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.spring.springboot.service.impl.UserServiceImpl;
//import org.spring.springboot.util.ThreadPoolUtil;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Component;
//
//
//
//@Component
//public class MsgConsumer {
//    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
//    @KafkaListener(groupId = "simpleGroup",topics = { "hxd" })
//    public void consumer(ConsumerRecord<String, String> consumerRecord) {
//        ThreadPoolUtil.executor(()-> this.gets(consumerRecord.value()));
//    }
//
//    private void gets(String data)
//    {
//        logger.info("消息消费--》" + data   + Thread.currentThread().getName());
//    }
//
//
//}
