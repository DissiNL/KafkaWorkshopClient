package com.dissi.kafkaworkshop.kafka.listeners;


import static com.dissi.kafkaworkshop.kafka.config.KafkaConstants.TOPIC_PET;

import com.dissi.kafkaworkshop.kafka.model.Pet;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.java.Log;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Log
@Service
public class PetShop extends AbstractConsumerSeekAware {

  /**
   * Contains all pets that currently have come in through Kafka.
   */
  private Map<Long, Pet> petStorages = new HashMap<>();

  /**
   * Seeks back to the beginning of the pets' topic. It will seek towards this beginning and replay all messages.
   *
   * @param assignments the new assignments and their current offsets.
   * @param callback the callback to perform an initial seek after assignment.
   */
  @Override
  public void onPartitionsAssigned(Map<TopicPartition, Long> assignments, ConsumerSeekCallback callback) {
    super.onPartitionsAssigned(assignments, callback);
    seekToBeginning();
  }

  /**
   * This method defines a simple listener that gets data from Kafka. It will display the data that it got from Kafka.
   *
   * @param data The incoming pet data from the kafka topic named "pets".
   */
  @KafkaListener(topics = TOPIC_PET)
  public void onMessage(@Payload(required = false) Pet data) {
    if (data != null) {
      log.info("Got message from kafka... " + data);
      petStorages.put(data.getId(), data);
    } else {
      log.info("Got Empty data form Kafka?!");

    }
  }
}
