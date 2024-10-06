package br.com.eighteenburguers.order.core.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class FindProductsUseCaseImplTest {

	@Mock
	ProductRepository repository;

	@Captor
	ArgumentCaptor<Long> captor;

	Faker faker;

	@BeforeEach
	void setup() {
		this.faker = Faker.instance();
	}
	
	@Test
	void shouldBeFindAllProducts() {
		FindProductsUseCase usecase = new FindProductsUseCaseImpl(repository);
		List<Product> products = usecase.execute();
		assertNotNull(products);
	}
}
