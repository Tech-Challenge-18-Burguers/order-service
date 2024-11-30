package br.com.eighteenburguers.order.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.order.adapter.OrderMapper;
import br.com.eighteenburguers.order.adapter.request.OrderItemRequest;
import br.com.eighteenburguers.order.adapter.request.OrderUpdateStatusRequest;
import br.com.eighteenburguers.order.adapter.response.OrderResponse;
import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.entity.order.OrderItem;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.usecase.order.CheckoutOrderUseCase;
import br.com.eighteenburguers.order.core.usecase.order.CreateOrderUseCase;
import br.com.eighteenburguers.order.core.usecase.order.FindAllOrdersUseCase;
import br.com.eighteenburguers.order.core.usecase.order.FindOrderByIdUseCase;
import br.com.eighteenburguers.order.core.usecase.order.UpdateOrderStatus;
import br.com.eighteenburguers.order.core.valueobject.order.OrderFilter;
import br.com.eighteenburguers.order.core.valueobject.pagination.PageData;
import br.com.eighteenburguers.order.core.valueobject.pagination.Pageable;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrderController {
	
	private final CheckoutOrderUseCase checkoutOrderUseCase;
	private final CreateOrderUseCase createOrderUseCase;
	private final FindAllOrdersUseCase findAllOrdersUseCase;
	private final FindOrderByIdUseCase findOrderByIdUseCase;
	private final UpdateOrderStatus updateOrderStatus;
	private final OrderMapper mapper;
	
	public PageData<OrderResponse> list(final OrderFilter filter, final Pageable pageable) {
		PageData<Order> page = findAllOrdersUseCase.execute(filter, pageable);
		List<OrderResponse> orders = page.getData().stream().map(mapper::toResponse).toList();
		return PageData.clone(orders, page);
	}
	
	public OrderResponse findById(final Long orderId) throws BusinessException {
		Order order = findOrderByIdUseCase.execute(orderId);
		return mapper.toResponse(order);
	}
	
	public OrderResponse create(final String customerId, final List<OrderItemRequest> request) throws BusinessException {
		List<OrderItem> items = request.stream().map(mapper::toItemEntity).toList();
		Order order = createOrderUseCase.execute(customerId, items);
		return mapper.toResponse(order);
	}
	
	public void checkout(final Long orderId) throws BusinessException {
		checkoutOrderUseCase.execute(orderId);
	}
	
	public OrderResponse update(final Long orderId, final OrderUpdateStatusRequest request) throws BusinessException {
		Order order = updateOrderStatus.execute(orderId, request.getStatus());
		return mapper.toResponse(order);
	}
}
