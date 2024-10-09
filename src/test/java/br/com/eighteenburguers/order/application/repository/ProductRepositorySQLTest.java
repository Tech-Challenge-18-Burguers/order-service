package br.com.eighteenburguers.order.application.repository;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.com.eighteenburguers.order.core.PageData;
import br.com.eighteenburguers.order.core.Pageable;
import br.com.eighteenburguers.order.core.entity.Category;
import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.repository.ProductRepository;
import br.com.eighteenburguers.order.core.valueobject.product.ProductFilter;
import br.com.eighteenburguers.order.infra.repository.ProductJpaRepository;
import br.com.eighteenburguers.order.infra.repository.entity.ProductEntity;
import br.com.eighteenburguers.order.mapper.ProductMapper;

@ExtendWith(MockitoExtension.class)
class ProductRepositorySQLTest {

	@Mock
	ProductJpaRepository jpaRepository;
	
	ProductMapper mapper;
	
	@BeforeEach
	void setup() {
		this.mapper = Mappers.getMapper(ProductMapper.class);
	}
	
	@Test
	@SuppressWarnings("unchecked")
	void shouldBeFindAll() {
		Mockito.when(jpaRepository.findAll(Mockito.any(Example.class), Mockito.any(PageRequest.class))).thenReturn(Mockito.mock(Page.class));
		ProductRepository repository = new ProductRepositorySQL(jpaRepository, mapper);
		PageData<Product> page = repository.findAll(new ProductFilter(), new Pageable());
		assertNotNull(page);
	}
	
	@Test
	void shouldBeFindById() {
		Mockito.when(jpaRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new ProductEntity()));
		ProductRepository repository = new ProductRepositorySQL(jpaRepository, mapper);
		Optional<Product> optional = repository.findById(Mockito.anyLong());
		assertTrue(optional.isPresent());
	}
	
	@Test
	void shouldBeFindByIdEmpty() {
		ProductRepository repository = new ProductRepositorySQL(jpaRepository, mapper);
		Optional<Product> optional = repository.findById(Mockito.anyLong());
		assertTrue(optional.isEmpty());
	}
	
	@Test
	void shouldBeSave() {
		ProductRepository repository = new ProductRepositorySQL(jpaRepository, mapper);
		repository.save(new Product());
		Mockito.verify(jpaRepository, Mockito.times(1)).save(Mockito.any());
	}
	
	@Test
	void shoulbBeDelete() {
		ProductRepository repository = new ProductRepositorySQL(jpaRepository, mapper);
		repository.delete(Mockito.anyLong());
		Mockito.verify(jpaRepository, Mockito.times(1)).deleteById(Mockito.anyLong());
	}
	
	@Test
	void shoulBeFindByCategoryId() {
		Mockito.when(jpaRepository.findByCategory(Mockito.any())).thenReturn(List.of());
		ProductRepository repository = new ProductRepositorySQL(jpaRepository, mapper);
		List<Product> products = repository.findByCategoryId(Category.ACOMPANHAMENTO);
		assertNotNull(products);
	}
}
