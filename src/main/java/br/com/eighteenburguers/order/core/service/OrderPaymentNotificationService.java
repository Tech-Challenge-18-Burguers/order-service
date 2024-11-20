package br.com.eighteenburguers.order.core.service;

import br.com.eighteenburguers.order.core.entity.order.Order;

public interface OrderPaymentNotificationService {

	void send(Order order);
}
