package br.com.eighteenburguers.order.core.usecase.order;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.entity.order.OrderStatus;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.InvalidOrderStatusException;
import br.com.eighteenburguers.order.core.exception.OrderNotFoundException;
import br.com.eighteenburguers.order.core.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class UpdateOrderStatusImplTest {

	@Mock
	OrderRepository repository;
	
	Faker faker;

	@BeforeEach
	void setup() {
		this.faker = Faker.instance();
	}
	
	@Test
	void shouldBeUpdate() throws BusinessException {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockOrder()));
		
		UpdateOrderStatus usecase = new UpdateOrderStatusImpl(repository);
		Order order = usecase.execute(faker.random().nextLong(), OrderStatus.PAID);
		assertEquals(OrderStatus.PAID, order.getStatus());
	}
	
	@Test
	void shouldBeUpdate2() throws BusinessException {
		Order persisted = mockOrder();
		persisted.setStatus(OrderStatus.PAID);
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(persisted));
		
		UpdateOrderStatus usecase = new UpdateOrderStatusImpl(repository);
		Order order = usecase.execute(faker.random().nextLong(), OrderStatus.IN_PREPARATION);
		assertEquals(OrderStatus.IN_PREPARATION, order.getStatus());
	}
	
	@Test
	void shouldBeUpdate3() throws BusinessException {
		Order persisted = mockOrder();
		persisted.setStatus(OrderStatus.READY);
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(persisted));
		
		UpdateOrderStatus usecase = new UpdateOrderStatusImpl(repository);
		Order order = usecase.execute(faker.random().nextLong(), OrderStatus.AWAITING_WITHDRAWAL);
		assertEquals(OrderStatus.AWAITING_WITHDRAWAL, order.getStatus());
	}
	
	@Test
	void shouldBeUpdate4() throws BusinessException {
		Order persisted = mockOrder();
		persisted.setStatus(OrderStatus.AWAITING_WITHDRAWAL);
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(persisted));
		
		UpdateOrderStatus usecase = new UpdateOrderStatusImpl(repository);
		Order order = usecase.execute(faker.random().nextLong(), OrderStatus.COMPLETED);
		assertEquals(OrderStatus.COMPLETED, order.getStatus());
	}
	
	@Test
	void shouldBeUpdate5() throws BusinessException {
		Order persisted = mockOrder();
		persisted.setStatus(OrderStatus.CREATED);
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(persisted));
		
		UpdateOrderStatus usecase = new UpdateOrderStatusImpl(repository);
		Order order = usecase.execute(faker.random().nextLong(), OrderStatus.AWAITING_PAYMENT);
		assertEquals(OrderStatus.AWAITING_PAYMENT, order.getStatus());
	}
	
	@Test
	void shouldBeNotUpdate() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		UpdateOrderStatus usecase = new UpdateOrderStatusImpl(repository);
		assertThrows(OrderNotFoundException.class, () -> usecase.execute(faker.random().nextLong(), OrderStatus.PAID));
	}
	
	@Test
	void shouldBeNotUpdateBecauseStatus() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockOrder()));
		UpdateOrderStatus usecase = new UpdateOrderStatusImpl(repository);
		assertThrows(InvalidOrderStatusException.class, () -> usecase.execute(faker.random().nextLong(), OrderStatus.READY));
	}

	private Order mockOrder() {
		return new Order(faker.random().nextLong(), UUID.randomUUID().toString(), List.of(), BigDecimal.ZERO, OrderStatus.AWAITING_PAYMENT, Instant.now(), Instant.now());
	}
}
