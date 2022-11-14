package com.dissi.kafkaworkshop.kafka.handler;

import java.nio.charset.StandardCharsets;
import java.util.function.Function;
import lombok.extern.java.Log;
import org.springframework.kafka.support.serializer.FailedDeserializationInfo;

/**
 * We can not use {@code ToLongFunction<FailedDeserializationInfo>} due to kafka requiring the error deserializer to be
 * a "Function".
 */
@SuppressWarnings("java:S4276")
@Log
public class DeserializerKeyHandler implements Function<FailedDeserializationInfo, Long> {

  public static final Long KEY_FAILURE = -1L;

  /**
   * Deserializing the incoming "Long" failed. We will return the value in {@link #KEY_FAILURE} to ensure we return a
   * default.
   *
   * @param failedDeserializationInfo the reason why the serialization failed. We can ignore this value.
   * @return The default {@link #KEY_FAILURE}.
   */
  public Long apply(FailedDeserializationInfo failedDeserializationInfo) {
    log.info(String.format("Got weird key [%s]",
      new String(failedDeserializationInfo.getData(), StandardCharsets.UTF_8)));
    return KEY_FAILURE;
  }
}
