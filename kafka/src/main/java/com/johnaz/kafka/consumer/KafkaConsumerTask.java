package com.johnaz.kafka.consumer;

import java.util.concurrent.Callable;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

/**
 * @author johnaz 2013-11-14 上午11:11:39
 */
public class KafkaConsumerTask implements Callable<Object> {

    private KafkaStream<byte[], byte[]> kafkaStream;

    private KafkaConsumerHandler        kafkaConsumerHandler;

    private KafkaConsumerListener       kafkaConsumerListener;

    public KafkaConsumerTask(KafkaStream<byte[], byte[]> kafkaStream, KafkaConsumerListener kafkaConsumerListener,
                             KafkaConsumerHandler kafkaConsumerHandler) {
        this.kafkaStream = kafkaStream;
        this.kafkaConsumerListener = kafkaConsumerListener;
        this.kafkaConsumerHandler = kafkaConsumerHandler;
    }

    public Object call() throws Exception {
        ConsumerIterator<byte[], byte[]> consumerIt = kafkaStream.iterator();
        while (consumerIt.hasNext()) {
            byte[] binaryMessage = consumerIt.next().message();
            String message = new String(binaryMessage, "UTF-8");
            if (kafkaConsumerListener != null) kafkaConsumerListener.onProcess();
            if (kafkaConsumerHandler != null) kafkaConsumerHandler.handle(message);
        }
        return null;
    }

}
