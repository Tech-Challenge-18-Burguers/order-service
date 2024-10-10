package br.com.eighteenburguers.order.core.usecase.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.order.core.entity.product.Category;
import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.ProductNotExistsException;
import br.com.eighteenburguers.order.core.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class DeleteProductByIdUseCaseImplTest {

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
	void shouldBeDeleteProduct() throws BusinessException {
		final Long id = faker.random().nextLong();

		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockProduct(id)));

		DeleteProductByIdUseCase usecase = new DeleteProductByIdUseCaseImpl(repository);
		usecase.execute(id);

		Mockito.verify(repository, Mockito.times(1)).findById(captor.capture());
		Mockito.verify(repository, Mockito.times(1)).delete(captor.capture());
		
		assertEquals(id, captor.getValue());
	}
	
	@Test
	void shouldBeNotDeleteProductBecauseProductNotFound() {
		final Long id = faker.random().nextLong();

		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		DeleteProductByIdUseCase usecase = new DeleteProductByIdUseCaseImpl(repository);
		assertThrows(ProductNotExistsException.class, () -> usecase.execute(id));

		Mockito.verify(repository, Mockito.times(1)).findById(captor.capture());
		Mockito.verify(repository, Mockito.times(0)).delete(Mockito.anyLong());
		
		assertEquals(id, captor.getValue());
	}

	Product mockProduct(Long id) {
		Product product = new Product(faker.name().name(), Category.LANCHE,
				BigDecimal.valueOf(faker.random().nextDouble()), faker.name().fullName(), "");
		product.setId(id);
		return product;
	}
}
