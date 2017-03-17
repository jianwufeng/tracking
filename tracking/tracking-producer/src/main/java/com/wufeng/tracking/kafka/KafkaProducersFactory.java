package com.wufeng.tracking.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducersFactory {
    Logger log = LoggerFactory.getLogger(this.getClass());

    public static String brokerList;
    public static String topics;
    public static Map<String, KafkaProducer> kafkaProducers = new HashMap<String, KafkaProducer>();
    public static final Map<String, List<String>> msgList = new HashMap<String, List<String>>();

    public KafkaProducersFactory(String brokerList, String topics) { // spring.xml中会进行构造函数初始化brokerList，topics
        KafkaProducersFactory.brokerList = brokerList;
        KafkaProducersFactory.topics = topics;
    }

    @PostConstruct
    public void createProducers() {
        log.info("brokerList={}", brokerList);
        log.info("to create producer={}", topics);
        String[] topicNames = topics.split(",");
        for (String topic : topicNames) {
            KafkaProducer producer = new KafkaProducer(topic);
            kafkaProducers.put(topic, producer);
            List<String> list = new ArrayList<String>();
            // 缓存数据
            msgList.put(topic, list);
        }
    }

    // @PostConstruct
    // public void createProducersTest() {
    // log.info("brokerList={}", brokerList);
    // log.info("to create producer={}", topics);
    // String[] topicNames = topics.split(",");
    // for (String topic : topicNames) {
    // KafkaProducer producer = new KafkaProducer(topic);
    // kafkaProducers.put(topic, producer);
    // List<String> list = new ArrayList<String>();
    // // 缓存数据
    // msgList.put(topic, list);
    // }
    // }

    // public static void main(String[] args) {
    // ApplicationContext context = new ClassPathXmlApplicationContext("spring/spring.xml");
    //
    // KafkaProducersFactory kafkaProducersFactory = (KafkaProducersFactory) context.getBean("kafkaProducersFactory");
    //
    // System.out.println(topics);
    // System.out.println(brokerList);
    // System.out.println(KafkaProducersFactory.msgList.size());
    // }
}
