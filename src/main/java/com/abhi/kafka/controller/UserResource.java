package com.abhi.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.kafka.model.User;
import com.abhi.kafka.model.UserProfile;

@RestController
public class UserResource {

	private static final String topic = "TOPIC";
	
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	@GetMapping("/post/{name}")
	public User post(@PathVariable("name") final String name) {
		User user = new User(name, "Tech", 20000000L);
		kafkaTemplate.send(topic, user);
		return user;
	}
	
	@GetMapping("/profile/{name}")
	public UserProfile postProfile(@PathVariable("name") final String name) {
		UserProfile userProfile = new UserProfile(name, "admin");
		kafkaTemplate.send(topic, userProfile);
		return userProfile;
	}
}
