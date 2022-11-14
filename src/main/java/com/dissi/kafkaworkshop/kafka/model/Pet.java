package com.dissi.kafkaworkshop.kafka.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pet {

  @JsonProperty("id")
  private Long id;

  @JsonProperty("createdAt")
  @DateTimeFormat(iso = ISO.DATE_TIME)
  private OffsetDateTime createdAt;

  @JsonProperty("name")
  private String name;

  @JsonProperty("type")
  private String type;

  @JsonProperty("owner")
  private String owner;

  public Pet id(Long id) {
    this.id = id;
    return this;
  }

}
