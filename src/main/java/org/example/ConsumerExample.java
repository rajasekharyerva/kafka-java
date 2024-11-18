package org.example;

import org.apache.kafka.clients.consumer.*;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerExample {
    public static void main(String[] args) {
        String topicName = "test-topic";

        // Set up consumer properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "test-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest"); // Start reading from the beginning

        // Create a Kafka consumer
        Consumer<String, String> consumer = new KafkaConsumer<>(props);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList(topicName));

        try {
            while (true) {
                // Poll for new data
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));

                for (ConsumerRecord<String, String> record : records) {
                    System.out.printf("Received message: key=%s, value=%s, topic=%s, partition=%d, offset=%d%n",
                            record.key(), record.value(), record.topic(), record.partition(), record.offset());
                }
            }
        } finally {
            // Close the consumer
            consumer.close();
        }
    }
}

