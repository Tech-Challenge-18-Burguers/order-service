package br.com.eighteenburguers.order.application.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.eighteenburguers.order.adapter.request.PaymentRequest;
import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.service.OrderPaymentNotificationService;
import br.com.eighteenburguers.order.infra.queue.QueueSqsMessageSender;
import io.awspring.cloud.sqs.operations.SqsTemplate;

@Service
public class OrderPaymentNotificationServiceImpl extends QueueSqsMessageSender<PaymentRequest>  implements OrderPaymentNotificationService {

	private static final String CONTEXT = "payment-request";
	
	public OrderPaymentNotificationServiceImpl(SqsTemplate template, @Value("${payment-queue}") String queue) {
		super(template, queue);
	}

	@Override
	public void send(Order order) {
		super.sendMessage(new PaymentRequest(order.getId(), order.getAmount()), CONTEXT);
	}

}
