package br.com.eighteenburguers.order.core.repository;

import java.util.Optional;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.valueobject.order.OrderFilter;
import br.com.eighteenburguers.order.core.valueobject.pagination.PageData;
import br.com.eighteenburguers.order.core.valueobject.pagination.Pageable;

public interface OrderRepository {

	PageData<Order> findAll(OrderFilter filter, Pageable pageable);
	
	Optional<Order> findById(Long id);
	
	Order save(Order order);
	
	Order update(Order order);
}
