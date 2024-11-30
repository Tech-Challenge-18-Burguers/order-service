package br.com.eighteenburguers.order.core.usecase.order;

import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.OrderNotFoundException;
import br.com.eighteenburguers.order.core.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class FindOrderByIdUseCaseImplTest {

	@Mock
	OrderRepository repository;
	
	Faker faker;

	@BeforeEach
	void setup() {
		this.faker = Faker.instance();
	}
	
	@Test
	void shouldBeFindById() throws BusinessException {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockOrder()));
		FindOrderByIdUseCase usecase = new FindOrderByIdUseCaseImpl(repository);
		Order order = usecase.execute(faker.random().nextLong());
		assertNotNull(order);
	}
	
	@Test
	void shouldBeNotFindById() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		FindOrderByIdUseCase usecase = new FindOrderByIdUseCaseImpl(repository);
		assertThrows(OrderNotFoundException.class, () -> usecase.execute(faker.random().nextLong()));
	}
	
	Order mockOrder() {
		Order order = new Order();
		order.setId(faker.random().nextLong());
		order.setCustomerId(UUID.randomUUID().toString());
		order.setAmount(BigDecimal.ZERO);
		order.setCreatedAt(Instant.now());
		order.setUpdatedAt(Instant.now());
		order.setItems(List.of());
		return order;
	}
}
