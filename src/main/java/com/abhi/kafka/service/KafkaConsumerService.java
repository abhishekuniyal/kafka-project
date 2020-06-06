package com.abhi.kafka.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.abhi.kafka.model.User;

@Service
public class KafkaConsumerService {

	@KafkaListener(topics = "TOPIC", id = "group_id")
	public void getTopicValues(String message) {
		System.out.println(message);
	}
	
	@KafkaListener(topics = "JSON_TOPIC", id = "json_group_id",
				containerFactory = "jsonKafkaListenerContainerFactory")
	public void getJsonTopicValues(Object message) {
		ConsumerRecord consumerRecord = (ConsumerRecord) message;
		
		System.out.println(consumerRecord.value());
	}
}
