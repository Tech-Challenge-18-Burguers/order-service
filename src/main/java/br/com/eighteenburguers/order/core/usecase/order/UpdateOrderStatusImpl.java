package br.com.eighteenburguers.order.core.usecase.order;

import static br.com.eighteenburguers.order.core.entity.order.OrderStatus.AWAITING_PAYMENT;
import static br.com.eighteenburguers.order.core.entity.order.OrderStatus.AWAITING_WITHDRAWAL;
import static br.com.eighteenburguers.order.core.entity.order.OrderStatus.CREATED;
import static br.com.eighteenburguers.order.core.entity.order.OrderStatus.IN_PREPARATION;
import static br.com.eighteenburguers.order.core.entity.order.OrderStatus.PAID;
import static br.com.eighteenburguers.order.core.entity.order.OrderStatus.READY;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.entity.order.OrderStatus;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.InvalidOrderStatusException;
import br.com.eighteenburguers.order.core.exception.OrderNotFoundException;
import br.com.eighteenburguers.order.core.repository.OrderRepository;

public class UpdateOrderStatusImpl implements UpdateOrderStatus {

	private final OrderRepository repository;

	public UpdateOrderStatusImpl(OrderRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Order execute(Long orderId, OrderStatus status) throws BusinessException {
		Order order = repository.findById(orderId).orElseThrow(OrderNotFoundException::new);

		boolean isValid = validate(order.getStatus(), status);

		if (!isValid) {
			throw new InvalidOrderStatusException();
		}

		order.setStatus(status);
		repository.save(order);
		return order;
	}

	private boolean validate(OrderStatus current, OrderStatus next) {

		switch (next) {
		case IN_PREPARATION:
			return current.equals(PAID);
		case PAID:
			return current.equals(AWAITING_PAYMENT);
		case READY:
			return current.equals(IN_PREPARATION);
		case AWAITING_WITHDRAWAL:
			return current.equals(READY);
		case COMPLETED:
			return current.equals(AWAITING_WITHDRAWAL);
		case AWAITING_PAYMENT:
			return current.equals(CREATED);
		case CANCELED:
			return current.equals(AWAITING_PAYMENT);
		default:
			return false;
		}

	}

}
