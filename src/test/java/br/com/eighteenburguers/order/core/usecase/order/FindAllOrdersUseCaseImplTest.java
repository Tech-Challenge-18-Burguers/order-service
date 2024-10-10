package br.com.eighteenburguers.order.core.usecase.order;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.repository.OrderRepository;
import br.com.eighteenburguers.order.core.valueobject.order.OrderFilter;
import br.com.eighteenburguers.order.core.valueobject.pagination.PageData;
import br.com.eighteenburguers.order.core.valueobject.pagination.Pageable;

@ExtendWith(MockitoExtension.class)
class FindAllOrdersUseCaseImplTest {

	@Mock
	OrderRepository repository;
	
	Faker faker;

	@BeforeEach
	void setup() {
		this.faker = Faker.instance();
	}
	
	@Test
	void shouldBeList() {
		Mockito.when(repository.findAll(Mockito.any(), Mockito.any())).thenReturn(mockPageData());
		
		FindAllOrdersUseCase usecase = new FindAllOrdersUseCaseImpl(repository);
		PageData<Order> page = usecase.execute(new OrderFilter(), new Pageable());
		assertNotNull(page);
	}

	private PageData<Order> mockPageData() {
		PageData<Order> page = new PageData<>();
		page.setData(List.of());
		return page;
	}
}
