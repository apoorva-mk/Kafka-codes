# Kafka-codes

Trying out the APIs in Java and Confluent-kafka python apis for clients.

- Start zookeeper
bin/zookeeper-server-start.sh config/zookeeper.properties
- Create a single cluster (localhost:9029)
bin/kafka-server-start.sh config/server.properties
- Create topic test, single partition, replication factor as 1
bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
