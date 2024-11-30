package br.com.eighteenburguers.order.infra.queue;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.order.adapter.request.OrderMessage;

@Configuration
public class MessageContextConfiguration {

	@Bean
	ContextConfiguration contextConfiguration() {
		ContextConfiguration configuration = new ContextConfiguration();
		configuration.configure("ORDER_PAID", OrderMessage.class);
		return configuration;
	}
}
