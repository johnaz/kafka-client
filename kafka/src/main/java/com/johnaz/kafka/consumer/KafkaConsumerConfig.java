/*
 * Copyright 2012-2014 glodon paas All right reserved. This software is the confidential and proprietary information of
 * glodon paas ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
 * in accordance with the terms of the license agreement you entered into with glodon paas.
 */
package com.johnaz.kafka.consumer;

import java.util.Properties;

import kafka.consumer.ConsumerConfig;

/**
 * @author johnaz 2013-11-14 上午9:55:32
 */
public class KafkaConsumerConfig extends ConsumerConfig {

    public static final String ZOOKEEPER_CONNECT                        = "zookeeper.connect";

    public static final String DEFAULT_ZOOKEEPER_CONNECT_VAL            = "localhost:2181";

    public static final String ZOOKEEPER_SESSION_TIMEOUT_MS             = "zookeeper.session.timeout.ms";

    public static final String DEFAULT_ZOOKEEPER_SESSION_TIMEOUT_MS_VAL = "4000";

    public static final String ZOOKEEPER_SYNC_TIME_MS                   = "zookeeper.sync.time.ms";

    public static final String DEFAULT_ZOOKEEPER_SYNC_TIME_MS_VAL       = "200";

    public static final String AUTO_COMMIT_INTERVAL_MS                  = "auto.commit.interval.ms";

    public static final String DEFAULT_AUTO_COMMIT_INTERVAL_MS_VAL      = "1000";

    public static final String GROUP_ID                                 = "group.id";

    public static final String DEFAULT_GROUP_ID_VAL                     = "defaultKafkaConsumerGroup";

    public KafkaConsumerConfig(Properties props) {
        super(props);
    }

    public static void setKafkaConsumerDefaultProperties(Properties props) {
        setDefaultPropertyIfNotExist(props, ZOOKEEPER_CONNECT, DEFAULT_ZOOKEEPER_CONNECT_VAL);
        setDefaultPropertyIfNotExist(props, GROUP_ID, DEFAULT_GROUP_ID_VAL);
        setDefaultPropertyIfNotExist(props, ZOOKEEPER_SESSION_TIMEOUT_MS, DEFAULT_ZOOKEEPER_SESSION_TIMEOUT_MS_VAL);
        setDefaultPropertyIfNotExist(props, ZOOKEEPER_SYNC_TIME_MS, DEFAULT_ZOOKEEPER_SYNC_TIME_MS_VAL);
        setDefaultPropertyIfNotExist(props, AUTO_COMMIT_INTERVAL_MS, DEFAULT_AUTO_COMMIT_INTERVAL_MS_VAL);
    }

    private static void setDefaultPropertyIfNotExist(Properties props, String key, String val) {
        if (!props.containsKey(key)) {
            props.setProperty(key, val);
        }
    }

}
