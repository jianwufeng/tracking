package com.wufeng.tracking.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wufeng.tracking.constants.TrackingKafkaConstant;
import com.wufeng.tracking.service.TrackingDetailProcessService;

/**
 * 处理kafka消息的消费者工厂
 * 
 *
 */
public class TrackingMessageConsumerFactory {

    private final ConsumerConnector consumer;
    private final String topic;
    private final String group;

    List<TrackingDetailProcessService> trackingDetailProcessServices;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrackingMessageConsumerFactory.class);

    public void setTrackingDetailProcessServices(List<TrackingDetailProcessService> trackingDetailProcessServices) {
        this.trackingDetailProcessServices = trackingDetailProcessServices;
    }

    public TrackingMessageConsumerFactory(Integer topicIndex, String groupName, String zookeeperConnect) {
        ConsumerConfig consumerConfig = createConsumerConfig(zookeeperConnect, groupName);
        consumer = Consumer.createJavaConsumerConnector(consumerConfig);
        if (topicIndex == null) {
            this.topic = TrackingKafkaConstant.TOPIC_PREFIX + 0;
        } else {
            this.topic = TrackingKafkaConstant.TOPIC_PREFIX + topicIndex;
        }

        this.group = groupName;
    }

    private static ConsumerConfig createConsumerConfig(String zookeeperConnect, String groupName) {
        Properties props = new Properties();
        props.put("zookeeper.connect", zookeeperConnect);
        props.put("group.id", groupName);
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.sync.time.ms", "4000");
        props.put("auto.commit.interval.ms", "2000");
        // props.put("rebalance.max.retries", "10");
        // props.put("rebalance.backoff.ms", "5000");
        return new ConsumerConfig(props);
    }

    @PostConstruct
    public void doConsume() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.submit(new Runnable() {

            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(10L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
                topicCountMap.put(topic, new Integer(1));
                Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
                KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
                ConsumerIterator<byte[], byte[]> it = stream.iterator();
                while (it.hasNext()) {
                    String message = new String(it.next().message());
                    for (TrackingDetailProcessService srv : trackingDetailProcessServices) {

                        LOGGER.error("=========================");
                        LOGGER.error(message);
                        LOGGER.error("=========================");

                        srv.doProcess(message);
                    }
                }
            }
        });

        // new Thread(new Runnable() {
        //
        // @Override
        // public void run() {
        // Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        // topicCountMap.put(topic, new Integer(1));
        // Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
        // KafkaStream<byte[], byte[]> stream = consumerMap.get(topic).get(0);
        // ConsumerIterator<byte[], byte[]> it = stream.iterator();
        // while (it.hasNext()) {
        // String message = new String(it.next().message());
        //
        // for (TrackingDetailProcessService srv : trackingDetailProcessServices) {
        // srv.doProcess(message);
        // }
        // }
        // }
        // }).start();
    }
}
