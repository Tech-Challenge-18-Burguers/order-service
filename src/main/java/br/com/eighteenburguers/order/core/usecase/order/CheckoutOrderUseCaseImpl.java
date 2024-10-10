package br.com.eighteenburguers.order.core.usecase.order;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.entity.order.OrderStatus;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.OrderNotFoundException;
import br.com.eighteenburguers.order.core.repository.OrderRepository;

public class CheckoutOrderUseCaseImpl implements CheckoutOrderUseCase {

	private final OrderRepository repository;

	public CheckoutOrderUseCaseImpl(OrderRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public void execute(Long orderId) throws BusinessException {
		Order order = repository.findById(orderId).orElseThrow(OrderNotFoundException::new);
		order.setStatus(OrderStatus.PAID);
		repository.save(order);
	}

}
