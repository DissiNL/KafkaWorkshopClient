package com.dissi.kafkaworkshop.kafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * Make sure we enable Kafka.
 * <a href="https://docs.spring.io/spring-boot/docs/2.7.4/reference/htmlsingle/#messaging.kafka">Details available on spring.</a>
 */
@EnableKafka
@SpringBootApplication
public class KafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaApplication.class, args);
	}

}
