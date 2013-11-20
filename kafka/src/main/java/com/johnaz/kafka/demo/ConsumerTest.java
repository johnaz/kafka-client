package com.johnaz.kafka.demo;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kafka.api.FetchRequest;
import kafka.api.FetchRequestBuilder;
import kafka.api.PartitionOffsetRequestInfo;
import kafka.cluster.Broker;
import kafka.common.ErrorMapping;
import kafka.common.TopicAndPartition;
import kafka.javaapi.FetchResponse;
import kafka.javaapi.OffsetRequest;
import kafka.javaapi.OffsetResponse;
import kafka.javaapi.PartitionMetadata;
import kafka.javaapi.TopicMetadata;
import kafka.javaapi.TopicMetadataRequest;
import kafka.javaapi.TopicMetadataResponse;
import kafka.javaapi.consumer.SimpleConsumer;
import kafka.message.MessageAndOffset;

/**
 * @author johnaz 2013-11-11 下午1:51:47
 */
public class ConsumerTest {

    public static void main(String[] args) {
        ConsumerTest example = new ConsumerTest();
        long maxReads = Long.parseLong("1");
        String topic = "t1";
        int partition = Integer.parseInt("0");
        List<String> seeds = new ArrayList<String>();
        seeds.add("172.16.233.57");
        int port = Integer.parseInt("9092");
        try {
            example.run(maxReads, topic, partition, seeds, port);
        } catch (Exception e) {
            System.out.println("Oops:" + e);
            e.printStackTrace();
        }
    }

    private List<String> m_replicaBrokers = new ArrayList<String>();

    public void run(long a_maxReads, String a_topic, int a_partition, List<String> a_seedBrokers, int a_port)
                                                                                                             throws Exception {
        PartitionMetadata metadata = findLeader(a_seedBrokers, a_port, a_topic, a_partition);
        if (metadata == null) {
            System.out.println("Can't find metadata for Topic and Partition. Exiting");
            return;
        }
        if (metadata.leader() == null) {
            System.out.println("Can't find Leader for Topic and Partition. Exiting");
            return;
        }
        String leaderBroker = metadata.leader().host();
        String clientName = "Client_" + a_topic + "_" + a_partition;
        SimpleConsumer consumer = new SimpleConsumer(leaderBroker, a_port, 100000, 64 * 1024, clientName);
        long readOffset = getLastOffset(consumer, a_topic, a_partition, kafka.api.OffsetRequest.LatestTime(),
                                        clientName);
        int numErrors = 0;
        while (a_maxReads > 0) {
            if (consumer == null) {
                consumer = new SimpleConsumer(leaderBroker, a_port, 100000, 64 * 1024, clientName);
            }
            FetchRequest req = new FetchRequestBuilder().clientId(clientName).addFetch(a_topic, a_partition,
                                                                                       readOffset, 100000).build();
            FetchResponse res = consumer.fetch(req);
            if (res.hasError()) {
                numErrors++;
                short code = res.errorCode(a_topic, a_partition);
                System.out.println("Error fetching data from the Broker:" + leaderBroker + " Reason: " + code);
                if (numErrors > 5) {
                    break;
                }
                if (code == ErrorMapping.OffsetOutOfRangeCode()) {
                    readOffset = getLastOffset(consumer, a_topic, a_partition, kafka.api.OffsetRequest.LatestTime(),
                                               clientName);
                    continue;
                }
                consumer.close();
                consumer = null;
                leaderBroker = findNewLeader(leaderBroker, a_topic, a_partition, a_port);
                continue;
            }
            numErrors = 0;

            long numRead = 0;
            for (MessageAndOffset messageAndOffset : res.messageSet(a_topic, a_partition)) {
                long currentOffset = messageAndOffset.offset();
                if (currentOffset < readOffset) {
                    System.out.println("Found an old offset: " + currentOffset + " Expecting: " + readOffset);
                    continue;
                }
                readOffset = messageAndOffset.nextOffset();
                ByteBuffer payload = messageAndOffset.message().payload();
                byte[] bytes = new byte[payload.limit()];
                payload.get(bytes);
                System.out.println(String.valueOf(messageAndOffset.offset()) + ": " + new String(bytes, "UTF8"));
                numRead++;
                // a_maxReads--;
            }
            // if (numRead == 0) {
            // try {
            // Thread.sleep(1);
            // } catch (InterruptedException ie) {
            // }
            // }
        }
        // if (consumer != null) consumer.close();
    }

    public static long getLastOffset(SimpleConsumer consumer, String topic, int partition, long whichTime,
                                     String clientName) {
        TopicAndPartition topicAndPartition = new TopicAndPartition(topic, partition);
        Map<TopicAndPartition, PartitionOffsetRequestInfo> requestInfo = new HashMap<TopicAndPartition, PartitionOffsetRequestInfo>();
        requestInfo.put(topicAndPartition, new PartitionOffsetRequestInfo(whichTime, 1));
        OffsetRequest offsetRequest = new OffsetRequest(requestInfo, kafka.api.OffsetRequest.CurrentVersion(),
                                                        clientName);
        OffsetResponse offsetResponse = consumer.getOffsetsBefore(offsetRequest);
        if (offsetResponse.hasError()) {
            System.out.println("Error fetching data Offset Data the Broker. Reason: "
                               + offsetResponse.errorCode(topic, partition));
            return 0;
        }
        long[] offsets = offsetResponse.offsets(topic, partition);
        return offsets[0];
    }

    private String findNewLeader(String a_oldLeader, String a_topic, int a_partition, int a_port) throws Exception {
        for (int i = 0; i < 3; i++) {
            boolean goToSleep = false;
            PartitionMetadata metadata = findLeader(m_replicaBrokers, a_port, a_topic, a_partition);
            if (metadata == null) {
                goToSleep = true;
            } else if (metadata.leader() == null) {
                goToSleep = true;
            } else {
                return metadata.leader().host();
            }
            if (goToSleep) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ie) {

                }
            }
        }
        System.out.println("Unable to find new leader after Broker failure. Exiting");
        throw new Exception("Unable to find new leader after Broker failure. Exiting");
    }

    private PartitionMetadata findLeader(List<String> a_seedBrokers, int a_port, String a_topic, int a_partition) {
        PartitionMetadata partitionMetadata = null;
        for (String seed : a_seedBrokers) {
            SimpleConsumer consumer = null;
            try {
                consumer = new SimpleConsumer(seed, a_port, 100000, 64 * 1024, "leaderLookUp");
                List<String> topics = new ArrayList<String>();
                topics.add(a_topic);
                TopicMetadataRequest req = new TopicMetadataRequest(topics);
                TopicMetadataResponse response = consumer.send(req);
                List<TopicMetadata> metaDatas = response.topicsMetadata();
                for (TopicMetadata metaData : metaDatas) {
                    for (PartitionMetadata part : metaData.partitionsMetadata()) {
                        if (part.partitionId() == a_partition) {
                            partitionMetadata = part;
                            break;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error communicating with Broker [" + seed + "] to find Leader for [" + a_topic
                                   + ", " + a_partition + "] Reason: " + e);
            } finally {
                if (consumer != null) consumer.close();
            }
        }
        if (partitionMetadata != null) {
            m_replicaBrokers.clear();
            for (Broker replica : partitionMetadata.replicas()) {
                m_replicaBrokers.add(replica.host());
            }
        }
        return partitionMetadata;
    }
}
