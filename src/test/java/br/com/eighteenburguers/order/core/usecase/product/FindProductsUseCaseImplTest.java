package br.com.eighteenburguers.order.core.usecase.product;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.repository.ProductRepository;
import br.com.eighteenburguers.order.core.valueobject.pagination.PageData;
import br.com.eighteenburguers.order.core.valueobject.pagination.Pageable;
import br.com.eighteenburguers.order.core.valueobject.product.ProductFilter;

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
		Mockito.when(repository.findAll(Mockito.any(), Mockito.any())).thenReturn(new PageData<Product>());
		FindProductsUseCase usecase = new FindProductsUseCaseImpl(repository);
		PageData<Product> products = usecase.execute(new ProductFilter(), new Pageable(1, 10));
		assertNotNull(products);
	}
}
