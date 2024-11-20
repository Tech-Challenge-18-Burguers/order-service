package br.com.eighteenburguers.order.core.usecase.order;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.OrderNotFoundException;
import br.com.eighteenburguers.order.core.repository.OrderRepository;
import br.com.eighteenburguers.order.core.service.OrderPaymentNotificationService;

public class CheckoutOrderUseCaseImpl implements CheckoutOrderUseCase {

	private final OrderRepository repository;
	private final OrderPaymentNotificationService notification;

	public CheckoutOrderUseCaseImpl(OrderRepository repository, OrderPaymentNotificationService notification) {
		this.repository = repository;
		this.notification = notification;
	}

	@Override
	public void execute(Long orderId) throws BusinessException {
		Order order = repository.findById(orderId).orElseThrow(OrderNotFoundException::new);
		notification.send(order);
	}

}
