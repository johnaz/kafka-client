package com.johnaz.kafka.demo;
///*
// * Copyright 2012-2014 glodon paas All right reserved. This software is the confidential and proprietary information of
// * glodon paas ("Confidential Information"). You shall not disclose such Confidential Information and shall use it only
// * in accordance with the terms of the license agreement you entered into with glodon paas.
// */
//package com.glodon.paas.coqui.kafka;
//
//import kafka.producer.Partitioner;
//import kafka.utils.VerifiableProperties;
//
///**
// * @author johnaz 2013-11-8 下午4:28:25
// */
//public class DefaultPartitioner implements Partitioner<String> {
//
//    public DefaultPartitioner(VerifiableProperties props) {
//
//    }
//
//    @Override
//    public int partition(String key, int a_numPartitions) {
//        int partition = 0;
//        int offset = key.lastIndexOf('.');
//        if (offset > 0) {
//            partition = Integer.parseInt(key.substring(offset + 1)) % a_numPartitions;
//        }
//        return partition;
//    }
//
//}
