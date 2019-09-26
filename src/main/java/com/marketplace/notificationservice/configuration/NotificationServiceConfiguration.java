package com.marketplace.notificationservice.configuration;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationServiceConfiguration {
	
	
	@Bean(name="RegQueue")
	public Queue directQueue() 
	{
		return new Queue("directQueue");
	}
	
	@Bean(name="directExchange")
	public DirectExchange exchange() {
		return new DirectExchange("directExchange");
	}
	
	@Bean(name="directBinding")
	public Binding directBinding(Queue queue,DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with("direct-key");
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory)
	{
		final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
		rabbitTemplate.setMessageConverter(jsonMessageConverter());
		return rabbitTemplate;
	}

}
