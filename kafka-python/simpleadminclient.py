from confluent_kafka.admin import AdminClient, NewTopic, NewPartitions, ConfigResource, ConfigSource
from confluent_kafka import KafkaException


#admin configuration
broker = 'localhost:9092'
a = AdminClient({'bootstrap.servers': broker})

#listing current topics
md = a.list_topics(timeout=10)
print(" {} topics:".format(len(md.topics)))
for topic in md.topics:
    print(topic)

#creating a new topic
topics = ["my-new-topic"]
new_topics = [NewTopic(topic, num_partitions=3, replication_factor=1) for topic in topics]
fs = a.create_topics(new_topics)
# Wait for operation to finish.
# Timeouts are preferably controlled by passing request_timeout=15.0
# to the create_topics() call.
# All futures will finish at the same time.
for topic, f in fs.items():
    try:
        f.result()  # The result itself is None
        print("Topic {} created".format(topic))
    except Exception as e:
        print("Failed to create topic {}: {}".format(topic, e))

#list to check newly created
for topic in md.topics:
    print(topic)

#deleting a topic
fs = a.delete_topics(topics, operation_timeout=30)

# Wait for operation to finish.
for topic, f in fs.items():
    try:
        f.result()  # The result itself is None
        print("Topic {} deleted".format(topic))
    except Exception as e:
        print("Failed to delete topic {}: {}".format(topic, e))


#list to check if deleted
for topic in md.topics:
    print(topic)