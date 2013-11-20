/*
 * Copyright 2012-2014 glodon paas All right reserved. This software is the confidential and proprietary information of
 * glodon paas ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with glodon paas.
 */
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
