package com.johnaz.kafka.demo;
///*
// * Copyright 2012-2014 glodon paas All right reserved. This software is the confidential and proprietary information of
// * glodon paas ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
// * in accordance with the terms of the license agreement you entered into with glodon paas.
// */
//package com.glodon.paas.coqui.kafka;
//
//import java.util.Date;
//import java.util.Properties;
//import java.util.Random;
//
//import kafka.javaapi.producer.Producer;
//import kafka.producer.KeyedMessage;
//import kafka.producer.ProducerConfig;
//
///**
// * @author johnaz 2013-11-8 下午4:19:41
// */
//public class ProducerTest {
//
//    public static void main(String[] args) {
//        long events = Long.parseLong("1");
//        Random rnd = new Random();
//
//        Properties props = new Properties();
//        props.put("metadata.broker.list", "172.16.233.57:9092");
//        props.put("serializer.class", "kafka.serializer.StringEncoder");
//
//        ProducerConfig config = new ProducerConfig(props);
//        Producer<String, String> producer = new Producer<String, String>(config);
//
//        for (long nEvents = 0; nEvents < events; nEvents++) {
//            long runtime = new Date().getTime();
//            String ip = "192.168.222." + rnd.nextInt(255);
//            String msg = runtime + ",www.example.com," + ip;
//            KeyedMessage<String, String> data = new KeyedMessage<String, String>("t1", ip, msg);
//            producer.send(data);
//        }
//        producer.close();
//    }
//
//}
