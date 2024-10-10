package br.com.eighteenburguers.order.core.usecase.order;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.OrderNotFoundException;
import br.com.eighteenburguers.order.core.repository.OrderRepository;

public class FindOrderByIdUseCaseImpl implements FindOrderByIdUseCase {

	private final OrderRepository repository;

	public FindOrderByIdUseCaseImpl(OrderRepository repository) {
		this.repository = repository;
	}

	@Override
	public Order execute(Long orderId) throws BusinessException {
		return repository.findById(orderId).orElseThrow(OrderNotFoundException::new);
	}
}
