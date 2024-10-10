package br.com.eighteenburguers.order.core.usecase.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
class FindProductByIdUseCaseImplTest {

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
	void shouldBeFindProductById() throws BusinessException {
		final Long id = faker.random().nextLong();

		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockProduct(id)));

		FindProductByIdUseCase usecase = new FindProductByIdUseCaseImpl(repository);
		Product product = usecase.execute(id);

		assertNotNull(product);
		assertEquals(id, product.getId());

		Mockito.verify(repository, Mockito.times(1)).findById(captor.capture());
		assertEquals(id, captor.getValue());
	}

	@Test
	void shouldBeNotFindBecauseNotFound() {

		final Long id = faker.random().nextLong();

		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

		FindProductByIdUseCase usecase = new FindProductByIdUseCaseImpl(repository);
		assertThrows(ProductNotExistsException.class, () -> usecase.execute(id));

		Mockito.verify(repository, Mockito.times(1)).findById(captor.capture());
		assertEquals(id, captor.getValue());
	}

	Product mockProduct(Long id) {
		Product product = new Product(faker.name().name(), Category.LANCHE,
				BigDecimal.valueOf(faker.random().nextDouble()), faker.name().fullName(), "");
		product.setId(id);
		return product;
	}
}
