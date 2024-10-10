package br.com.eighteenburguers.order.core.usecase.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.entity.order.OrderItem;
import br.com.eighteenburguers.order.core.entity.order.OrderStatus;
import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.repository.OrderRepository;
import br.com.eighteenburguers.order.core.repository.ProductRepository;

public class CreateOrderUseCaseImpl implements CreateOrderUseCase {

	private final OrderRepository orderRepository;
	private final ProductRepository productRepository;

	public CreateOrderUseCaseImpl(OrderRepository orderRepository, ProductRepository productRepository) {
		this.orderRepository = orderRepository;
		this.productRepository = productRepository;
	}

	@Override
	public Order execute(final String customerId, final List<OrderItem> items) throws BusinessException {

		List<OrderItem> orderItems = this.mapProducts(items);

		Order order = new Order(customerId, orderItems);
		order.calculateAmount();
		order.setStatus(OrderStatus.AWAITING_PAYMENT);
		return orderRepository.save(order);
	}

	private List<OrderItem> mapProducts(List<OrderItem> items) {
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		List<Long> ids = items.stream().map(OrderItem::getProduct).map(Product::getId).collect(Collectors.toList());

		List<Product> products = productRepository.findByIds(ids);

		for (OrderItem item : items) {
			Optional<Product> optional = products.stream()
					.filter(product -> product.getId().equals(item.getProduct().getId())).findFirst();

			if (optional.isPresent()) {
				orderItems.add(new OrderItem(optional.get(), item.getQuantity(), item.getObservation()));
			}
		}

		return orderItems;
	}

}
