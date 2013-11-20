/*
 * Copyright 2012-2014 glodon paas All right reserved. This software is the confidential and proprietary information of
 * glodon paas ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with glodon paas.
 */
package com.johnaz.kafka.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author johnaz 2013-11-14 下午3:26:31
 */
public class DefaultKafkaProducer implements KafkaProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultKafkaProducer.class);

    private ProducerConfig      producerConfig;

    public DefaultKafkaProducer(Properties props) {
        this.producerConfig = new ProducerConfig(props);
    }

    public DefaultKafkaProducer(String brokerList, String serializerClass) {
        Properties props = new Properties();
        if (StringUtils.isNotBlank(brokerList)) props.put(KafkaProducerConfig.METADATA_BROKER_LIST, brokerList);
        if (StringUtils.isNotBlank(serializerClass)) props.put(KafkaProducerConfig.SERIALIZER_CLASS,
                                                               KafkaProducerConfig.DEFAULT_SERIALIZER_CLASS);
        this.producerConfig = new ProducerConfig(props);
    }

    public void send(String topic, String msg) {
        KeyedMessage<String, String> keyedMessage = new KeyedMessage<String, String>(topic, msg);
        List<KeyedMessage<String, String>> keyedMessages = new ArrayList<KeyedMessage<String, String>>();
        keyedMessages.add(keyedMessage);
        send(keyedMessages);
    }

    public void send(List<KeyedMessage<String, String>> keyedMessages) {
        Producer<String, String> producer = null;
        try {
            producer = new Producer<String, String>(producerConfig);

            producer.send(keyedMessages);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            throw new KafkaProducerException(e.getMessage(), e);
        } finally {
            if (producer != null) producer.close();
        }
    }

}
