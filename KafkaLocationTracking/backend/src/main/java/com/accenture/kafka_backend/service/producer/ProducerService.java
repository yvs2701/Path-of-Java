package com.accenture.kafka_backend.service.producer;

import java.util.concurrent.ExecutionException;

public interface ProducerService<T> {
	void sendMessage(T t) throws InterruptedException, ExecutionException;
}
