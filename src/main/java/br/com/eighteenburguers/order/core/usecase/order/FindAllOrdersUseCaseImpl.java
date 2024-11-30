package br.com.eighteenburguers.order.core.usecase.order;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.repository.OrderRepository;
import br.com.eighteenburguers.order.core.valueobject.order.OrderFilter;
import br.com.eighteenburguers.order.core.valueobject.pagination.PageData;
import br.com.eighteenburguers.order.core.valueobject.pagination.Pageable;

public class FindAllOrdersUseCaseImpl implements FindAllOrdersUseCase {

	private final OrderRepository repository;

	public FindAllOrdersUseCaseImpl(OrderRepository repository) {
		this.repository = repository;
	}

	@Override
	public PageData<Order> execute(OrderFilter filter, Pageable pageable) {
		return repository.findAll(filter, pageable);
	}

}
