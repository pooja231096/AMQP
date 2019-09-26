package com.marketplace.notificationservice.controller;



import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.marketplace.notificationservice.model.MailResponse;
import com.marketplace.notificationservice.model.Notification;
import com.marketplace.notificationservice.model.RegistrationDataTemplate;
import com.marketplace.notificationservice.service.EmailService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class NotificationServiceController {
	
	@Autowired
	EmailService service;
	
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private DirectExchange directExchange;
	
	
	Map<String, Object> model = new HashMap<String, Object>();

	@GetMapping("/send")
	public String sendNotification() 
	{
		rabbitTemplate.convertAndSend(directExchange.getName(),"direct-key",new Notification(1,"sathwik"));
		return "Done";
	}
	
	
	@GetMapping("/receiveData")
	public String receiveNotificaion() 
	{
		Message message = rabbitTemplate.receive("RegistrationDataQueue");
		RegistrationDataTemplate registrationDataTemplate = new Gson().fromJson(new String(message.getBody()), RegistrationDataTemplate.class);
		System.out.println(new String(message.getBody()));
		System.out.println(registrationDataTemplate);
		model.put("name", registrationDataTemplate.getUsername());
		model.put("id",registrationDataTemplate.getToken());
		model.put("email", registrationDataTemplate.getEmail());
		return new String(message.getBody());
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
//	@RabbitListener(queues="RegistrationDataQueue")
	@GetMapping("/sendMail")
	public MailResponse sendEmail() {
		Message message = rabbitTemplate.receive("RegistrationDataQueue");
		RegistrationDataTemplate registrationDataTemplate = new Gson().fromJson(new String(message.getBody()), RegistrationDataTemplate.class);
		System.out.println(new String(message.getBody()));
		model.put("name", registrationDataTemplate.getUsername());
		model.put("id",registrationDataTemplate.getToken());
		model.put("email", registrationDataTemplate.getEmail());
		model.put("userId",registrationDataTemplate.getId());
		System.out.println(model.get("email"));
		return service.sendEmail(model);

	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@GetMapping("/reset")
	public MailResponse resetPassword() {
		model.clear();
		Message message = rabbitTemplate.receive("PasswordResetQueue");
		RegistrationDataTemplate registrationDataTemplate = new Gson().fromJson(new String(message.getBody()), RegistrationDataTemplate.class);
		model.put("name", registrationDataTemplate.getUsername());
		model.put("id",registrationDataTemplate.getToken());
		model.put("email", registrationDataTemplate.getEmail());
		return service.resetPassword(model);

	}
	
}
