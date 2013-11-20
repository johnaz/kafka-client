package com.johnaz.kafka.consumer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.Consumer;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * @author johnaz 2013-11-14 上午9:59:57
 */
public class DefaultKafkaConsumer implements KafkaConsumer {

    private String                topic;

    private KafkaConsumerConfig   kafkaConsumerConfig;

    private ConsumerConnector     kafkaConsumerConnector;

    private KafkaConsumerListener kafkaConsumerListener;

    private KafkaConsumerHandler  kafkaConsumerHandler;

    private ExecutorService       executorService = Executors.newCachedThreadPool();

    public DefaultKafkaConsumer(Properties props, String topic) {
        this.topic = topic;
        this.kafkaConsumerConfig = createKafkaConsumerConfig(props);
        this.kafkaConsumerConnector = Consumer.createJavaConsumerConnector(kafkaConsumerConfig);
    }

    public DefaultKafkaConsumer(String zookeeperCluster, String groupId, String topic) {
        this.topic = topic;
        this.kafkaConsumerConfig = createKafkaConsumerConfig(zookeeperCluster, groupId);
        this.kafkaConsumerConnector = Consumer.createJavaConsumerConnector(kafkaConsumerConfig);
    }

    private KafkaConsumerConfig createKafkaConsumerConfig(String zookeeperCluster, String groupId) {
        Properties props = new Properties();
        props.put(KafkaConsumerConfig.ZOOKEEPER_CONNECT, zookeeperCluster);
        props.put(KafkaConsumerConfig.GROUP_ID, groupId);
        KafkaConsumerConfig.setKafkaConsumerDefaultProperties(props);
        return new KafkaConsumerConfig(props);
    }

    private KafkaConsumerConfig createKafkaConsumerConfig(Properties props) {
        KafkaConsumerConfig.setKafkaConsumerDefaultProperties(props);
        return new KafkaConsumerConfig(props);
    }

    public void startConsuming() {
        // Create topicCountMap
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(this.topic, new Integer(1));

        // Create consumerMap
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = kafkaConsumerConnector.createMessageStreams(topicCountMap);

        // Get kafkaStrem
        List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(this.topic);

        // Start a thread to consume
        if (streams != null && streams.size() > 0) {
            KafkaStream<byte[], byte[]> stream = streams.get(0);
            if (kafkaConsumerListener != null) kafkaConsumerListener.onStart();
            executorService.submit(new KafkaConsumerTask(stream, this.kafkaConsumerListener, this.kafkaConsumerHandler));
        }
    }

    public void close() {
        if (this.kafkaConsumerConnector != null) kafkaConsumerConnector.shutdown();
        if (this.executorService != null) this.executorService.shutdown();
        if (this.kafkaConsumerListener != null) this.kafkaConsumerListener.onClose();
    }

    public void setKafkaConsumerListener(KafkaConsumerListener listener) {
        this.kafkaConsumerListener = listener;
    }

    public void setKafkaConsumerHandler(KafkaConsumerHandler handler) {
        this.kafkaConsumerHandler = handler;
    }

}
