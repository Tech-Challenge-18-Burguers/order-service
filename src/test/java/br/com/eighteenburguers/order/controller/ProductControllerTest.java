package br.com.eighteenburguers.order.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.github.javafaker.Faker;

import br.com.eighteenburguers.order.adapter.ProductMapper;
import br.com.eighteenburguers.order.adapter.request.ProductRequest;
import br.com.eighteenburguers.order.adapter.response.ProductResponse;
import br.com.eighteenburguers.order.core.entity.product.Category;
import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.usecase.product.CreateProductUseCase;
import br.com.eighteenburguers.order.core.usecase.product.DeleteProductByIdUseCase;
import br.com.eighteenburguers.order.core.usecase.product.FindProductByCategoryUseCase;
import br.com.eighteenburguers.order.core.usecase.product.FindProductByIdUseCase;
import br.com.eighteenburguers.order.core.usecase.product.FindProductsUseCase;
import br.com.eighteenburguers.order.core.usecase.product.UpdateProductUseCase;
import br.com.eighteenburguers.order.core.valueobject.pagination.PageData;
import br.com.eighteenburguers.order.core.valueobject.pagination.Pageable;
import br.com.eighteenburguers.order.core.valueobject.product.ProductFilter;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

	@Mock
	CreateProductUseCase createProductUseCase;

	@Mock
	FindProductsUseCase findProductsUseCase;

	@Mock
	FindProductByIdUseCase findProductByIdUseCase;

	@Mock
	FindProductByCategoryUseCase findProductByCategoryUseCase;

	@Mock
	UpdateProductUseCase updateProductUseCase;

	@Mock
	DeleteProductByIdUseCase deleteProductByIdUseCase;

	ProductMapper mapper;

	ProductController controller;

	Faker faker;

	@BeforeEach
	void setup() {
		this.faker = Faker.instance();
		this.mapper = Mappers.getMapper(ProductMapper.class);
		this.controller = new ProductController(createProductUseCase, findProductsUseCase, findProductByIdUseCase,
				findProductByCategoryUseCase, updateProductUseCase, deleteProductByIdUseCase, mapper);
	}

	@Test
	void shouldBeList() throws BusinessException {
		Mockito.when(findProductsUseCase.execute(Mockito.any(), Mockito.any())).thenReturn(mockPageData());
		PageData<ProductResponse> page = controller.list(mockFilter(), new Pageable());
		assertNotNull(page);
	}

	@Test
	void shouldBeFindById() throws BusinessException {
		Mockito.when(findProductByIdUseCase.execute(Mockito.anyLong())).thenReturn(new Product());
		ProductResponse response = controller.findById(Mockito.anyLong());
		assertNotNull(response);
	}

	@Test
	void shouldBeFindByCategoryId() throws BusinessException {
		Mockito.when(findProductByCategoryUseCase.execute(Mockito.anyInt())).thenReturn(List.of());
		List<ProductResponse> response = controller.findByCategoryId(Mockito.anyInt());
		assertNotNull(response);
	}

	@Test
	void shoulbBeCreate() throws BusinessException {
		Mockito.when(createProductUseCase.execute(Mockito.any())).thenReturn(new Product());
		ProductResponse response = controller.create(new ProductRequest());
		assertNotNull(response);
	}

	@Test
	void shoulbBeUpdate() throws BusinessException {
		Mockito.when(updateProductUseCase.execute(Mockito.anyLong(), Mockito.any())).thenReturn(new Product());
		ProductResponse response = controller.update(1L, new ProductRequest());
		assertNotNull(response);
	}

	@Test
	void shouldBeDelete() throws BusinessException {
		controller.delete(Mockito.anyLong());
		Mockito.verify(deleteProductByIdUseCase, Mockito.times(1)).execute(Mockito.anyLong());
	}

	PageData<Product> mockPageData() {
		PageData<Product> page = new PageData<>();
		page.setData(List.of());
		return page;
	}

	Product mockProduct() {
		Product product = new Product(faker.name().name(), Category.LANCHE,
				BigDecimal.valueOf(faker.random().nextDouble()), faker.name().name(), faker.internet().url());
		product.setId(faker.random().nextLong());
		return product;
	}
	
	ProductFilter mockFilter() {
		ProductFilter filter = new ProductFilter();
		filter.setCategory(Category.LANCHE);
		filter.setDescription(faker.name().name());
		filter.setName(faker.name().name());
		filter.setPrice(BigDecimal.ONE);
		return filter;
	}
}
