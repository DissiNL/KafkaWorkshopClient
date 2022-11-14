package com.dissi.kafkaworkshop.kafka.handler;

import com.dissi.kafkaworkshop.kafka.model.Pet;
import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import lombok.extern.java.Log;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

@Log
public class DeserializerValueHandler implements Function<FailedDeserializationInfo, Pet> {

  public static final Pet VALUE_FAILURE = new Pet().id(-1L);

  /**
   * Deserializing the Pet is impossible for whatever reason. There are 2 reasons this can occur:
   * <ul>
   *   <li>The message is malformed</li>
   *   <li>There is a tombstone message</li>
   * </ul>
   * <pre>
   * When there is no payload null is returned.
   * When there is a malformed payload the {@link #VALUE_FAILURE} is returned.
   * </pre>
   *
   * @param failedDeserializationInfo the reason why the serialization failed. We can ignore this value.
   * @return null or a failed deserialized pet.
   */
  public Pet apply(FailedDeserializationInfo failedDeserializationInfo) {
    if (failedDeserializationInfo.getData().length == 0) {
      // Tombstone
      return null;
    }

    log.info(String.format("Got weird message [%s]",
      new String(failedDeserializationInfo.getData(), StandardCharsets.UTF_8)));
    return VALUE_FAILURE;
  }
}
