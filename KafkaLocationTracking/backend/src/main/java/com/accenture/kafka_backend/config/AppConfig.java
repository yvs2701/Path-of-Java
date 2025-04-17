package com.accenture.kafka_backend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AppConfig {
	private final String KafkaTopicName;

	@Autowired
	public AppConfig(@Value("${conf.kafka-topic}") String KafkaTopicName) {
		this.KafkaTopicName = KafkaTopicName;
	}

	@Bean
	public NewTopic topic() {
		return TopicBuilder.name(KafkaTopicName).build();
	}
}
