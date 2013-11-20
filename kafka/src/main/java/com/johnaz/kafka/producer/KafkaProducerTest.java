/*
 * Copyright 2012-2014 glodon paas All right reserved. This software is the confidential and proprietary information of
 * glodon paas ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with glodon paas.
 */
package com.johnaz.kafka.producer;

import java.util.Properties;

/**
 * @author johnaz 2013-11-14 下午4:01:50
 */
public class KafkaProducerTest {

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("metadata.broker.list", "172.16.233.57:9092");
        props.put("serializer.class", "kafka.serializer.StringEncoder");

        KafkaProducer kafkaProducer = new DefaultKafkaProducer(props);
        kafkaProducer.send("t1", "1233");
    }

}
