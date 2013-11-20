/*
 * Copyright 2012-2014 glodon paas All right reserved. This software is the confidential and proprietary information of
 * glodon paas ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with glodon paas.
 */
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
