package com.johnaz.kafka.demo;
///*
// * Copyright 2012-2014 glodon paas All right reserved. This software is the confidential and proprietary information of
// * glodon paas ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
// * in accordance with the terms of the license agreement you entered into with glodon paas.
// */
//package com.glodon.paas.coqui.kafka;
//
//import java.util.concurrent.Callable;
//
//import kafka.consumer.ConsumerIterator;
//import kafka.consumer.KafkaStream;
//
///**
// * @author johnaz 2013-11-12 上午11:31:15
// */
//public class EasyConsumerTask implements Callable<Object> {
//
//    private KafkaStream<byte[], byte[]> kafkaStream;
//
//    private int                         threadNum;
//
//    public EasyConsumerTask(KafkaStream<byte[], byte[]> kafkaStream, int threadNum) {
//        this.kafkaStream = kafkaStream;
//        this.threadNum = threadNum;
//    }
//
//    @Override
//    public Object call() throws Exception {
//        ConsumerIterator<byte[], byte[]> it = kafkaStream.iterator();
//        while (it.hasNext()) {
//            System.out.println("Thread " + threadNum + ": " + new String(it.next().message(), "UTF-8"));
//        }
//        System.out.println("Shutting down thread : " + threadNum);
//        return null;
//    }
//
//}
