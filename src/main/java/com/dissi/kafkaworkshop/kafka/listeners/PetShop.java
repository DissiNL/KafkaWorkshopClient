package com.dissi.kafkaworkshop.kafka.listeners;


import static com.dissi.kafkaworkshop.kafka.config.KafkaConstants.TOPIC_PET;
import static com.dissi.kafkaworkshop.kafka.handler.DeserializerKeyHandler.KEY_FAILURE;
import static com.dissi.kafkaworkshop.kafka.handler.DeserializerValueHandler.VALUE_FAILURE;

import com.dissi.kafkaworkshop.kafka.model.Pet;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.AbstractConsumerSeekAware;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Log
@Service
@RequiredArgsConstructor
public class PetShop extends AbstractConsumerSeekAware {

  /**
   * This field defines what we send towards Kafka. It provides us with information on what we need to send.
   */
  private final KafkaTemplate<Long, Pet> petKafkaTemplate;

  /**
   * Contains all pets that currently have come in through Kafka.
   */
  private final Map<Long, Pet> petStorages = new HashMap<>();

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
   * @param key They key that is related to the message that came in.
   * @param data The incoming pet data from the kafka topic named "pets".
   */
  @KafkaListener(topics = TOPIC_PET)
  public void onMessage(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) Long key, @Payload(required = false) Pet data) {
    if (KEY_FAILURE.equals(key)) {
      // Really broken message....
      return;
    }

    if (data != null) {
      if (VALUE_FAILURE.equals(data)) {
        log.warning("Got weird pet with ID -1");
        // Ohno broken.. We send an update!
        Pet pet = petStorages.getOrDefault(key, new Pet(key, OffsetDateTime.now(), "Mark", "Human", "its-me!"));
        petKafkaTemplate.send(TOPIC_PET, key, pet);
      } else {
        log.info("Got message from kafka... " + data);
        petStorages.put(data.getId(), data);
      }
    } else {
      log.info(String.format("Deleting [%s]", key));
      petStorages.remove(key);
    }

    log.info(String.format("Size of storage: [%s]", petStorages.size()));
  }
}
