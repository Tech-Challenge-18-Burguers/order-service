package br.com.eighteenburguers.order.core.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.order.core.entity.Category;
import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class CreateProductUseCaseImplTest {

	@Mock
	ProductRepository repository;

	Faker faker;

	@BeforeEach
	void setup() {
		this.faker = Faker.instance();
	}

	@Test
	void shouldBeCreateNewProduct() throws BusinessException {
		Product product = mockProduct(); 
		Mockito.when(repository.save(Mockito.any())).thenReturn(product);
		CreateProductUseCase usecase = new CreateProductUseCaseImpl(repository);
		Product response = usecase.execute(product);
		assertEquals(response.getName(), product.getName());
	}
	
	Product mockProduct() {
		return new Product(faker.name().name(), Category.LANCHE,
				BigDecimal.valueOf(faker.random().nextDouble()), faker.name().fullName(), "");
	}
}
