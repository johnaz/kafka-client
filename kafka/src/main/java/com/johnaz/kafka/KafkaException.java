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
