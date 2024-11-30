package br.com.eighteenburguers.order.core.usecase.order;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.entity.order.OrderItem;
import br.com.eighteenburguers.order.core.entity.order.OrderStatus;
import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.repository.OrderRepository;
import br.com.eighteenburguers.order.core.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class CreateOrderUseCaseImplTest {

	@Mock
	OrderRepository orderRepository;
	
	@Mock
	ProductRepository productRepository;
	
	Faker faker;
	
	@Captor
	ArgumentCaptor<Order> captor;

	@BeforeEach
	void setup() {
		this.faker = Faker.instance();
	}
	
	@Test
	void shouldBeCreate() throws BusinessException {
		
		Mockito.when(productRepository.findByIds(Mockito.anyList())).thenReturn(mockProducts());
		
		CreateOrderUseCase usecase = new CreateOrderUseCaseImpl(orderRepository, productRepository);
		usecase.execute(UUID.randomUUID().toString(), mockOrderItems());
		
		Mockito.verify(orderRepository, Mockito.times(1)).save(captor.capture());
		assertEquals(BigDecimal.valueOf(30.0), captor.getValue().getAmount());
		assertEquals(OrderStatus.AWAITING_PAYMENT, captor.getValue().getStatus());
	}
	
	List<OrderItem> mockOrderItems() {
		List<OrderItem> list = new ArrayList<>();
		
		for(int i=1;i<=3;i++) {
			Product product = new Product();
			product.setId(Long.valueOf(i));
			OrderItem item = new OrderItem(product, 1, "");
			list.add(item);
		}
		
		return list;
	}
	
	List<Product> mockProducts() {
		List<Product> list = new ArrayList<>();
		for(int i=1;i<=3;i++) {
			Product product = new Product();
			product.setId(Long.valueOf(i));
			product.setPrice(BigDecimal.valueOf(10.0));
			list.add(product);
		}
		return list;
	}
}
