package com.apoorva.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.DescribeConfigsResult;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.config.ConfigResource;
import java.util.Collections;
import java.util.Properties;
import java.util.*;
import java.util.concurrent.ExecutionException;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.clients.admin.NewTopic;

public class SimpleAdminClient {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
      Properties config = new Properties();
      config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

      //Describe nodes in cluster details
      System.out.println("\n\nDescribing cluster");
      AdminClient admin = AdminClient.create(config);
      for (Node node : admin.describeCluster().nodes().get()) {
          System.out.println("-- node: " + node.id() + " --");
          ConfigResource cr = new ConfigResource(ConfigResource.Type.BROKER, "0");
          DescribeConfigsResult dcr = admin.describeConfigs(Collections.singleton(cr));
          dcr.all().get().forEach((k, c) -> {
              c.entries()
               .forEach(configEntry -> {System.out.println(configEntry.name() + "= " + configEntry.value());});
          });
      }

      //Listing all topics in a cluster
      for (TopicListing topicListing : admin.listTopics().listings().get()) {
          System.out.println(topicListing);
      }


      //Creating a dummy topic
      System.out.println("\n\nCreating topic....");
      NewTopic newTopic = new NewTopic("my-new-topic", 1, (short) 1);
      admin.createTopics(Collections.singleton(newTopic));

      //listing
      System.out.println("\n\nListing topics....");
      admin.listTopics().names().get().forEach(System.out::println);

      //Deleting a dummy topic
      System.out.println("\n\nDeleting topic....");
      ArrayList<String> delList=new ArrayList<String>();
      delList.add("my-new-topic");
      admin.deleteTopics(delList);

      //listing
      System.out.println("\n\nListing topics");
      admin.listTopics().names().get().forEach(System.out::println);

  }
}
