package com.apoorva.kafka;

import java.util.*;
import org.apache.kafka.clients.producer.*;

/**
 * Simple Producer API for single node cluster
 * Docs: https://kafka.apache.org/25/javadoc/index.html?org/apache/kafka/clients/producer/KafkaProducer.html
 *
 */


public class SimpleProducer
{
    public static void main(String[] args) throws Exception{
           
        Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");

		//To ensure complete full commit to record, blocking write
		props.put("acks", "all");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		System.out.println("Configuration Completed.");
		Producer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 10; i++)
		    producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i)));

		System.out.println("Message sent to topic - test");
		producer.close();
   }
}
