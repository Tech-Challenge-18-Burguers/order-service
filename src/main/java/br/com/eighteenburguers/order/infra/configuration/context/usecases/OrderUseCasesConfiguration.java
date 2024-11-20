package br.com.eighteenburguers.order.infra.configuration.context.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.order.core.repository.OrderRepository;
import br.com.eighteenburguers.order.core.repository.ProductRepository;
import br.com.eighteenburguers.order.core.service.OrderPaymentNotificationService;
import br.com.eighteenburguers.order.core.usecase.order.CheckoutOrderUseCase;
import br.com.eighteenburguers.order.core.usecase.order.CheckoutOrderUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.order.CreateOrderUseCase;
import br.com.eighteenburguers.order.core.usecase.order.CreateOrderUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.order.FindAllOrdersUseCase;
import br.com.eighteenburguers.order.core.usecase.order.FindAllOrdersUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.order.FindOrderByIdUseCase;
import br.com.eighteenburguers.order.core.usecase.order.FindOrderByIdUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.order.UpdateOrderStatus;
import br.com.eighteenburguers.order.core.usecase.order.UpdateOrderStatusImpl;

@Configuration
public class OrderUseCasesConfiguration {

	@Bean
	CheckoutOrderUseCase checkoutOrderUseCase(OrderRepository repository, OrderPaymentNotificationService notification) {
		return new CheckoutOrderUseCaseImpl(repository, notification);
	}
	
	@Bean
	CreateOrderUseCase createOrderUseCase(OrderRepository orderRepository, ProductRepository productRepository) {
		return new CreateOrderUseCaseImpl(orderRepository, productRepository);
	}
	
	@Bean
	FindAllOrdersUseCase findAllOrdersUseCase(OrderRepository repository) {
		return new FindAllOrdersUseCaseImpl(repository);
	}
	
	@Bean
	FindOrderByIdUseCase findOrderByIdUseCase(OrderRepository repository) {
		return new FindOrderByIdUseCaseImpl(repository);
	}
	
	@Bean
	UpdateOrderStatus updateOrderStatus(OrderRepository repository) {
		return new UpdateOrderStatusImpl(repository);
	}
	
	
}
