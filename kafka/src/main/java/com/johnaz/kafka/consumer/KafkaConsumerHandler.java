/*
 * Copyright 2012-2014 glodon paas All right reserved. This software is the confidential and proprietary information of
 * glodon paas ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with glodon paas.
 */
package com.johnaz.kafka.consumer;

/**
 * @author user 2013-11-14 上午11:25:06
 */
public interface KafkaConsumerHandler {

    public abstract void handle(String msg);

}
