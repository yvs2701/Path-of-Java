package com.accenture.kafka_backend.service.producer;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.accenture.kafka_backend.entity.LocationEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LocationProducer implements ProducerService<LocationEntity> {
	private final String KafkaTopicName;
	private final KafkaTemplate<String, LocationEntity> kafkaTemplate;

	@Autowired
	public LocationProducer(@Value("${conf.kafka-topic}") String kafkaTopicName,
			KafkaTemplate<String, LocationEntity> kafkaTemplate) {
		KafkaTopicName = kafkaTopicName;
		this.kafkaTemplate = kafkaTemplate;
	}

	public void sendMessage(LocationEntity loc) throws InterruptedException, ExecutionException {
		Future<SendResult<String, LocationEntity>> futureResult = kafkaTemplate.send(KafkaTopicName, loc);
		SendResult<String, LocationEntity> result = futureResult.get();
		log.info("Message sent: " + result.getProducerRecord().value());
	}
}
