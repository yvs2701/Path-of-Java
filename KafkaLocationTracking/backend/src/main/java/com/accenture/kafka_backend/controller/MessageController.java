package com.accenture.kafka_backend.controller;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.kafka_backend.entity.LocationEntity;
import com.accenture.kafka_backend.service.producer.ProducerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/kafka")
@Slf4j
public class MessageController {
	private final ProducerService<LocationEntity> kafkaProducer;

	@Autowired
	public MessageController(ProducerService<LocationEntity> kafkaProducer) {
		this.kafkaProducer = kafkaProducer;
	}

	@PostMapping(value = "/publish", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Map<String, String>> publishMessage(@RequestBody LocationEntity loc)
			throws InterruptedException, ExecutionException {
		log.info("Incoming POST request @ /publish : " + loc);
		kafkaProducer.sendMessage(loc);
		return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "Message Sent!"));
	}

	@ExceptionHandler(value = { InterruptedException.class, ExecutionException.class }, produces = "application/json")
	public ResponseEntity<Map<String, String>> catchAllPublishExceptions(Exception e) {
		// TODO - Write detailed error handling logic (less priority)
		log.error("Error occured while trying to publish!");
		e.printStackTrace();
		return ResponseEntity.status(500).body(Map.of("message", e.getMessage()));
	}
}
