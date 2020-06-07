package com.abhi.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
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
	@Async
	public User post(@PathVariable("name") final String name) {
		/**while going for Async , u can use below configs. Also while using Aycn u might loose the ordering
		 * 
		 * synchronous send
		 * max.in.flight.requests.per.connection
		 
		 */
		User user = new User(name, "Tech", 20000000L);
		ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(topic, user);
		future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {

			@Override
			public void onSuccess(SendResult<String, Object> message) {
				// TODO Auto-generated method stub
				System.out.println("sent message= " + message + " with offset= " + message.getRecordMetadata().offset());
			}

			@Override
			public void onFailure(final Throwable throwable) {
				// TODO Auto-generated method stub
				System.out.println("unable to send message= " + user +":"+":"+ throwable);
			}
		});
		return user;
	}
	
	@GetMapping("/profile/{name}")
	public UserProfile postProfile(@PathVariable("name") final String name) {
		UserProfile userProfile = new UserProfile(name, "admin");
		kafkaTemplate.send(topic, userProfile);
		return userProfile;
	}
}
