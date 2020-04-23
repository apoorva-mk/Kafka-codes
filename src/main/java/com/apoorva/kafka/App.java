package com.apoorva.kafka;

import java.util.*;
import org.apache.kafka.clients.producer.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) throws Exception{
           
        Properties props = new Properties();
		props.put("bootstrap.servers", "localhost:9092");
		props.put("acks", "all");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		System.out.println("Configuration Completed.");
		Producer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 100; i++)
		    producer.send(new ProducerRecord<String, String>("test", Integer.toString(i), Integer.toString(i)));

		System.out.println("Message sent to topic - test");
		producer.close();
   }
}
