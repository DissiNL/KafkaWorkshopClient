package com.dissi.kafkaworkshop.kafka.config;


/**
 * A class containing constants related to the petshop Kafka.
 * Topics are prefixed with TOPIC_* to indicate the usage is intended for a topic.
 */
public class KafkaConstants {

  private KafkaConstants() {
    // Hiding default constructor.
  }

  // The name of the topic that will be used throughout the workshop.
  public static final String TOPIC_PET = "pets";
}
