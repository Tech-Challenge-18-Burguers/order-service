package br.com.eighteenburguers.order.application.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.service.OrderPaymentNotificationService;
import io.awspring.cloud.sqs.operations.SqsTemplate;

@ExtendWith(MockitoExtension.class)
public class OrderPaymentNotificationServiceImplTest {

	@Mock
	SqsTemplate template;
	
	@Test
	void shouldBeSendMessage() {
		Order order = new Order();
		order.setId(1L);
		
		OrderPaymentNotificationService service = new OrderPaymentNotificationServiceImpl(template, "queue");
		assertDoesNotThrow(() -> service.send(order));
	}
}
