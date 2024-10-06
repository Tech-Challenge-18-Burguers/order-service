package br.com.eighteenburguers.order.core.usecase;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.order.core.entity.Category;
import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class FindProductByCategoryUseCaseImplTest {

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
	void shouldBeFindProductByCategory() throws BusinessException {
		final Integer id = faker.random().nextInt(1, 4);
		FindProductByCategoryUseCase usecase = new FindProductByCategoryUseCaseImpl(repository);
		List<Product> products = usecase.execute(id);
		
		assertNotNull(products);
	}
	
	Product mockProduct(Long id) {
		Product product = new Product(faker.name().name(), Category.LANCHE,
				BigDecimal.valueOf(faker.random().nextDouble()), faker.name().fullName(), "");
		product.setId(id);
		return product;
	}
}
