package com.johnaz.kafka.consumer;

/**
 * @author johnaz 2013-11-14 上午9:57:11
 */
public interface KafkaConsumer {

    public abstract void startConsuming();

    public abstract void close();

    public abstract void setKafkaConsumerListener(KafkaConsumerListener listener);

    public abstract void setKafkaConsumerHandler(KafkaConsumerHandler handler);

}
