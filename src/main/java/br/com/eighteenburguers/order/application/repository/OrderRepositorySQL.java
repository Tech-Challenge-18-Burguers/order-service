package br.com.eighteenburguers.order.application.repository;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import br.com.eighteenburguers.order.adapter.OrderMapper;
import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.repository.OrderRepository;
import br.com.eighteenburguers.order.core.valueobject.order.OrderFilter;
import br.com.eighteenburguers.order.core.valueobject.pagination.PageData;
import br.com.eighteenburguers.order.core.valueobject.pagination.Pageable;
import br.com.eighteenburguers.order.infra.repository.OrderJpaRepository;
import br.com.eighteenburguers.order.infra.repository.entity.OrderEntity;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class OrderRepositorySQL implements OrderRepository {

	private final OrderJpaRepository repository;
	private final OrderMapper mapper;

	@Override
	public PageData<Order> findAll(OrderFilter filter, Pageable pageable) {
		Example<OrderEntity> example = Example.of(mapper.toEntity(filter),
				ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase());
		Page<OrderEntity> page = repository.findAll(example, pageable.getPageRequest());
		return PageData.from(page.getContent().stream().map(mapper::toEntity).toList(), page);
	}

	@Override
	public Optional<Order> findById(Long id) {
		Optional<OrderEntity> optional = repository.findById(id);
		return optional.isPresent() ? Optional.of(mapper.toEntity(optional.get())) : Optional.empty();
	}

	@Override
	public Order save(Order order) {
		OrderEntity entity = repository.save(mapper.toJpaEntity(order));
		return mapper.toEntity(entity);
	}

	@Override
	public Order update(Order order) {
		OrderEntity entity = repository.save(mapper.toJpaEntity(order));
		return mapper.toEntity(entity);
	}

}
