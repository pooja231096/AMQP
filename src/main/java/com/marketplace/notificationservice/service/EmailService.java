package com.marketplace.notificationservice.service;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.marketplace.notificationservice.model.MailResponse;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class EmailService {
	// Map<String, Object> model = new HashMap<String, Object>();

	@Autowired
	private JavaMailSender sender;

	@Autowired
	private Configuration config;

//@RabbitListener(queues = "RegistrationDataQueue")
	public MailResponse sendEmail(Map<String, Object> model) {
//		Message messages = rabbitTemplate.receive("RegistrationDataQueue");
//		RegistrationDataTemplate registrationDataTemplate = new Gson().fromJson(new String(messages.getBody()), RegistrationDataTemplate.class);
////		model.put("name", registrationDataTemplate.getUsername());
//		model.put("id",registrationDataTemplate.getToken());
//		model.put("email", registrationDataTemplate.getEmail());
		MailResponse response = new MailResponse();
 
		System.out.println("Eureka!");
		MimeMessage message = sender.createMimeMessage();
		try {
			// set mediaType
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.ISO_8859_1.name());
			// add attachment
//			helper.addAttachment("logo.png", new ClassPathResource("logo.png"));

			Template t = config.getTemplate("email-template.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);
			String to = String.valueOf(model.get("email"));
			System.out.println(to);
			helper.setTo(InternetAddress.parse(to));
			helper.setText(html, true);
			helper.setSubject("Verify your account");
			sender.send(message);

			response.setMessage("mail send to : " + to);
			response.setStatus(Boolean.TRUE);

		} catch (Exception e) {
			response.setMessage("Mail Sending failure : " + e.getMessage());
			response.setStatus(Boolean.FALSE);
		}

		System.out.println(response);

		return response;
	}

	public MailResponse resetPassword(Map<String, Object> model) {
		MailResponse response = new MailResponse();
		MimeMessage message = sender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.ISO_8859_1.name());
//			helper.addAttachment("logo.png", new ClassPathResource("logo.png"));
			Template t = config.getTemplate("email-template2.ftl");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

			System.out.println(model.get("email"));
			String to = (String) model.get("email");
			String from = "sathwik2319@gmail.com";
			helper.setTo(to);
			helper.setText(html, true);
			helper.setSubject("Verify your account");
			helper.setFrom(from);
			sender.send(message);

			response.setMessage("mail send to : " + to);
			response.setStatus(Boolean.TRUE);

		} catch (Exception e) {
			response.setMessage("Mail Sending failure : " + e.getMessage());
			response.setStatus(Boolean.FALSE);
		}

		return response;
	}

}