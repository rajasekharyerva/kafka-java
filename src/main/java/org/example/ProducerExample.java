package org.example;
import org.apache.kafka.clients.producer.*;
import java.util.Properties;

public class ProducerExample {
    public static void main(String[] args) {
        String topicName = "test-topic";

        // Set up producer properties
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        // Create a Kafka producer
        Producer<String, String> producer = new KafkaProducer<>(props);

        for (int i = 1; i <= 10; i++) {
            String key = "Key-" + i;
            String value = "Message-" + i;

            // Create and send a record
            ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);

            producer.send(record, (metadata, exception) -> {
                if (exception == null) {
                    System.out.printf("Sent message: key=%s, value=%s, topic=%s, partition=%d, offset=%d%n",
                            key, value, metadata.topic(), metadata.partition(), metadata.offset());
                } else {
                    exception.printStackTrace();
                }
            });
        }

        // Close the producer
        producer.close();
    }
}

