package com.johnaz.kafka.producer;

import java.util.List;

import kafka.producer.KeyedMessage;

/**
 * @author johnaz 2013-11-14 下午3:26:03
 */
public interface KafkaProducer {

    public abstract void send(String topic, String msg);

    public abstract void send(List<KeyedMessage<String, String>> KeyedMessage);

}
