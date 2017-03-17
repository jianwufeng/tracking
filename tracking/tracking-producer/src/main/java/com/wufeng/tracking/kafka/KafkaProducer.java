package com.wufeng.tracking.kafka;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wufeng.tracking.constants.TrackingKafkaConstant;

/**
 * 往kafka服务推送消息的生产者
 *
 */
public class KafkaProducer {
    Logger log = LoggerFactory.getLogger(this.getClass());

    private Producer<Integer, String> producer;
    private String topic;

    public KafkaProducer(String topic) {
        Properties props = new Properties();
        props.put("serializer.class", TrackingKafkaConstant.STRING_ENCODER);
        props.put("metadata.broker.list", KafkaProducersFactory.brokerList);
        producer = new Producer<Integer, String>(new ProducerConfig(props));
        this.topic = topic;
    }

    public void produceMsg(String message) {
        log.info("Send: {} to topic: {}", message, topic);
        producer.send(new KeyedMessage<Integer, String>(topic, message));
    }

    public void produceBatchedMsg(List<String> messages) {
        log.info("Send BatchedMsg {}  {} : to topic: {}", messages.size(), new Date(), topic);
        for (String msg : messages) {
            producer.send(new KeyedMessage<Integer, String>(topic, msg));
            log.error(msg);
        }

    }

    public void dispose() {
        producer.close();
    }

}