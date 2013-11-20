/*
 * Copyright 2012-2014 glodon paas All right reserved. This software is the confidential and proprietary information of
 * glodon paas ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with glodon paas.
 */
package com.johnaz.kafka;

/**
 * @author johnaz 2013-11-14 下午3:36:34
 */
public abstract class KafkaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public KafkaException(String msg) {
        super(msg);
    }

    public KafkaException(String msg, Throwable e) {
        super(msg, e);
    }

}
