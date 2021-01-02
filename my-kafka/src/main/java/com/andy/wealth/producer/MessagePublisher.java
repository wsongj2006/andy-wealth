package com.andy.wealth.producer;


import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MessagePublisher {
    private final static String topic = "andy-topic-1";

    public static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.25.128:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "my_transactional_id");
        return properties;
    }

    /**
     * 发后即忘，不care发送是否成功
     * @param message
     */
    public static void publish(String message, KafkaProducer<String, String> producer) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, message);
        producer.send(producerRecord);
    }

    public static void publishInTransactional(String message, KafkaProducer<String, String> producer) {
        producer.initTransactions();
        producer.beginTransaction();
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, message);
        producer.send(producerRecord);
        producer.send(producerRecord);
        producer.abortTransaction();
    }

    /**
     * 同步发送，每发送一条都等待发送结果
     * @param message
     */
    public static void publishSync(String message, KafkaProducer<String, String> producer) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, message);
        try {
            RecordMetadata metadata = producer.send(producerRecord).get();
            System.out.println(metadata);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步发送，设置callback，producer在发送成功或者失败之后调用callback.
     * @param message
     */
    public static void publishAsync(String message, KafkaProducer<String, String> producer) {
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>(topic, message);
        producer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    System.out.println("Message sent successful, and the response is: " + recordMetadata);
                } else {
                    System.out.println("Message sent failed...");
                }
            }
        });
    }

    public static void main(String[] args) {
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(initConfig());
        String message = "Hello, this is Andy -- 4";
        publishInTransactional(message, producer);
        producer.close();

    }
}
