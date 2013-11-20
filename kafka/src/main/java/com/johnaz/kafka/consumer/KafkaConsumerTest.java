/*
 * Copyright 2012-2014 glodon paas All right reserved. This software is the confidential and proprietary information of
 * glodon paas ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with glodon paas.
 */
package com.johnaz.kafka.consumer;

/**
 * @author johnaz 2013-11-14 上午11:37:29
 */
public class KafkaConsumerTest {

    public static void main(String[] args) {
        KafkaConsumer kafkaConsumer = new DefaultKafkaConsumer("172.16.233.57:2181", "testGroup", "t1");
        kafkaConsumer.setKafkaConsumerHandler(new KafkaConsumerHandler() {

            public void handle(String msg) {
                System.out.println(msg);
            }
        });
        kafkaConsumer.startConsuming();
    }

}
