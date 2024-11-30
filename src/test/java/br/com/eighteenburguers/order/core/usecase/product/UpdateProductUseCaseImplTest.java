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
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.order.core.entity.product.Category;
import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.ProductNotExistsException;
import br.com.eighteenburguers.order.core.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class UpdateProductUseCaseImplTest {

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
	void shouldBeUpdateProduct() throws BusinessException {
		Long id = faker.random().nextLong();
		Product product = new Product(faker.name().name(), Category.ACOMPANHAMENTO,
				BigDecimal.valueOf(faker.random().nextDouble()), faker.name().fullName(), "Updated");
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(mockProduct(id)));
		Answer<Product> answer = new Answer<Product>() {
			
			@Override
			public Product answer(InvocationOnMock invocation) throws Throwable {
				Product param = invocation.getArgument(0);
				Product persisted = new Product();
				persisted.setCategory(param.getCategory());
				persisted.setDescription(param.getDescription());
				persisted.setImage(param.getImage());
				persisted.setName(param.getName());
				persisted.setPrice(param.getPrice());
				persisted.setId(param.getId());
				
				return persisted;
			}
		};
		Mockito.when(repository.save(Mockito.any())).thenAnswer(answer);
		
		
		UpdateProductUseCase usecase = new UpdateProductUseCaseImpl(repository);
		Product updated = usecase.execute(id , product);
		
		assertEquals(id, updated.getId());
	}
	
	@Test
	void shouldBeNotUpdateBecauseNotFound() {
		Long id = faker.random().nextLong();
		Product product = new Product(faker.name().name(), Category.ACOMPANHAMENTO,
				BigDecimal.valueOf(faker.random().nextDouble()), faker.name().fullName(), "Updated");
		
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		
		UpdateProductUseCase usecase = new UpdateProductUseCaseImpl(repository);
		assertThrows(ProductNotExistsException.class, () -> usecase.execute(id , product));
	}
	
	Product mockProduct(Long id) {
		Product product = new Product(faker.name().name(), Category.LANCHE,
				BigDecimal.valueOf(faker.random().nextDouble()), faker.name().fullName(), "");
		product.setId(id);
		return product;
	}
}
