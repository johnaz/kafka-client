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
