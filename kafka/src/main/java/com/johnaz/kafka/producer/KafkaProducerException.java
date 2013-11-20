/*
 * Copyright 2012-2014 glodon paas All right reserved. This software is the confidential and proprietary information of
 * glodon paas ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with glodon paas.
 */
package com.johnaz.kafka.producer;

/**
 * @author johnaz 2013-11-14 下午3:38:15
 */
public class KafkaProducerException extends com.johnaz.kafka.KafkaException {

    private static final long serialVersionUID = 1L;

    public KafkaProducerException(String msg) {
        super(msg);
    }

    public KafkaProducerException(String msg, Throwable e) {
        super(msg, e);
    }

}
