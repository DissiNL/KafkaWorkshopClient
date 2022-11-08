package com.dissi.kafkaworkshop.kafka.listeners;


import static com.dissi.kafkaworkshop.kafka.config.KafkaConstants.TOPIC_PET;

import com.dissi.kafkaworkshop.kafka.model.Pet;
import lombok.extern.java.Log;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Log
@Service
public class PetShop {

  /**
   * This method defines a simple listener that gets data from Kafka. It will display the data that it got from Kafka.
   *
   * @param data The incoming pet data from the kafka topic named "pets".
   */
  @KafkaListener(topics = TOPIC_PET)
  public void onMessage(@Payload(required = false) Pet data) {
    //TODO Add "business logic"
    log.info("Got message from kafka... " + data);
  }
}
