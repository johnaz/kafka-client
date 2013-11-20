package com.johnaz.kafka.demo;

// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;
// import java.util.Properties;
// import java.util.concurrent.ExecutorService;
// import java.util.concurrent.Executors;
//
// import kafka.consumer.ConsumerConfig;
// import kafka.consumer.KafkaStream;
// import kafka.javaapi.consumer.ConsumerConnector;
//
// /**
// * @author johnaz 2013-11-12 上午11:14:59
// */
// public class EasyConsumerTest {
//
// private final ConsumerConnector consumer;
// private final String topic;
// private ExecutorService executor;
//
// public EasyConsumerTest(String zookeeper, String groupId, String topic) {
// this.consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig(zookeeper, groupId));
// this.topic = topic;
// }
//
// public void shutDown() {
// if (consumer != null) consumer.shutdown();
// if (executor != null) executor.shutdown();
// }
//
// public void run(int thread) {
// Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
// topicCountMap.put(this.topic, thread);
// Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
// List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(this.topic);
// executor = Executors.newFixedThreadPool(thread);
//
// int threadNumber = 0;
// for (final KafkaStream<byte[], byte[]> stream : streams) {
// executor.submit(new EasyConsumerTask(stream, threadNumber));
// threadNumber++;
// }
// }
//
// private ConsumerConfig createConsumerConfig(String zookeeper, String groupId) {
// Properties props = new Properties();
// props.put("zookeeper.connect", zookeeper);
// props.put("group.id", groupId);
// props.put("zookeeper.session.timeout.ms", "400");
// props.put("zookeeper.sync.time.ms", "200");
// props.put("auto.commit.interval.ms", "1000");
// return new ConsumerConfig(props);
// }
//
// public static void main(String[] args) {
// String zookeeper = "172.16.233.57";
// String groupId = "test-easy-consumer";
// String topic = "t1";
// EasyConsumerTest test = new EasyConsumerTest(zookeeper, groupId, topic);
// test.run(1);
// // try {
// // Thread.sleep(100000);
// // } catch (Exception e) {
// //
// // }
// // test.shutDown();
// }
//
// }
