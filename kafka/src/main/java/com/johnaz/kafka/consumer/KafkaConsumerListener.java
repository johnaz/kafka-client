package com.johnaz.kafka.consumer;

/**
 * @author johnaz 2013-11-14 上午11:09:02
 */
public interface KafkaConsumerListener {

    public void onStart();

    public void onProcess();

    public void onClose();

}
