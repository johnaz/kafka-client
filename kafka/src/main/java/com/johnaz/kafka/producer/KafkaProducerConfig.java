/*
 * Copyright 2012-2014 glodon paas All right reserved. This software is the confidential and proprietary information of
 * glodon paas ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with glodon paas.
 */
package com.johnaz.kafka.producer;

import java.util.Properties;

import kafka.producer.ProducerConfig;

/**
 * @author johnaz 2013-11-14 下午3:27:55
 */
public class KafkaProducerConfig extends ProducerConfig {

    public static final String METADATA_BROKER_LIST         = "metadata.broker.list";

    public static final String DEFAULT_METADATA_BROKER_LIST = "localhost:9092";

    public static final String SERIALIZER_CLASS             = "serializer.class";

    public static final String DEFAULT_SERIALIZER_CLASS     = "kafka.serializer.StringEncoder";

    public KafkaProducerConfig(Properties props) {
        super(props);
    }

    public void setDefaultKafkaProducerProperties(Properties props) {
        setDefaultPropertyIfNotExist(props, METADATA_BROKER_LIST, DEFAULT_METADATA_BROKER_LIST);
        setDefaultPropertyIfNotExist(props, SERIALIZER_CLASS, DEFAULT_SERIALIZER_CLASS);
    }

    private static void setDefaultPropertyIfNotExist(Properties props, String key, String val) {
        if (!props.containsKey(key)) {
            props.setProperty(key, val);
        }
    }

}
