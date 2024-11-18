[Kafka](https://kafka.apache.org/downloads)

Steps to Run the Program
Start Zookeeper:

bash
Copy code
bin/zookeeper-server-start.sh config/zookeeper.properties
Start Kafka Broker:

bash
Copy code
bin/kafka-server-start.sh config/server.properties
Create a Topic:

bash
Copy code
bin/kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1
Run the Producer:

Step 2: List Topics
To verify the topic:

bash
Copy code
bin/kafka-topics.sh --list --bootstrap-server localhost:9092


Compile and run ProducerExample to send messages.
Run the Consumer:

Compile and run ConsumerExample to read messages.

Step 3: Produce Messages
Start a producer to send messages:

bash
Copy code
bin/kafka-console-producer.sh --topic test-topic --bootstrap-server localhost:9092
Type a few messages and press Enter.

Step 4: Consume Messages
Start a consumer to read messages:

bash
Copy code
bin/kafka-console-consumer.sh --topic test-topic --from-beginning --bootstrap-server localhost:9092
