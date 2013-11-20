package com.johnaz.kafka.consumer;

/**
 * @author user 2013-11-14 上午11:25:06
 */
public interface KafkaConsumerHandler {

    public abstract void handle(String msg);

}
