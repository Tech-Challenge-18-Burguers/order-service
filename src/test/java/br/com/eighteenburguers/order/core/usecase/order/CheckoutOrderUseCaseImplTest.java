package br.com.eighteenburguers.order.core.usecase.order;

import static org.junit.jupiter.api.Assertions.assertThrows;

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
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.OrderNotFoundException;
import br.com.eighteenburguers.order.core.repository.OrderRepository;
import br.com.eighteenburguers.order.core.service.OrderPaymentNotificationService;

@ExtendWith(MockitoExtension.class)
class CheckoutOrderUseCaseImplTest {

	@Mock
	OrderRepository repository;
	
	@Mock
	OrderPaymentNotificationService notification;
	
	Faker faker;

	@BeforeEach
	void setup() {
		this.faker = Faker.instance();
	}
	
	@Test
	void shouldBeCheckout() throws BusinessException {
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockOrder()));
		
		CheckoutOrderUseCase usecase = new CheckoutOrderUseCaseImpl(repository, notification);
		usecase.execute(faker.random().nextLong());
		
		Mockito.verify(repository, Mockito.times(1)).findById(Mockito.anyLong());
		Mockito.verify(notification, Mockito.times(1)).send(Mockito.any());
	}

	@Test
	void shouldBeNotCheckout() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		CheckoutOrderUseCase usecase = new CheckoutOrderUseCaseImpl(repository, notification);
		assertThrows(OrderNotFoundException.class, () -> usecase.execute(faker.random().nextLong()));
		
		Mockito.verify(repository, Mockito.times(1)).findById(Mockito.anyLong());
		Mockito.verify(notification, Mockito.times(0)).send(Mockito.any());
	}
	
	Order mockOrder() {
		return new Order(faker.random().nextLong(), UUID.randomUUID().toString(), List.of());
	}
}
