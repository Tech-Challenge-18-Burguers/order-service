package br.com.eighteenburguers.order.application.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import br.com.eighteenburguers.order.adapter.ProductMapper;
import br.com.eighteenburguers.order.core.entity.product.Category;
import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.repository.ProductRepository;
import br.com.eighteenburguers.order.core.valueobject.pagination.PageData;
import br.com.eighteenburguers.order.core.valueobject.pagination.Pageable;
import br.com.eighteenburguers.order.core.valueobject.product.ProductFilter;
import br.com.eighteenburguers.order.infra.repository.ProductJpaRepository;
import br.com.eighteenburguers.order.infra.repository.entity.ProductEntity;
import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ProductRepositorySQL implements ProductRepository {

	private final ProductJpaRepository repository;
	private final ProductMapper mapper;

	@Override
	public PageData<Product> findAll(ProductFilter filter, Pageable pageable) {
		Example<ProductEntity> example = Example.of(mapper.toJpaEntity(filter),
				ExampleMatcher.matching().withStringMatcher(StringMatcher.CONTAINING).withIgnoreCase());
		Page<ProductEntity> page = repository.findAll(example, pageable.getPageRequest());
		List<Product> products = page.getContent().stream().map(mapper::toEntity).toList();
		return PageData.from(products, page);
	}

	@Override
	public Optional<Product> findById(Long id) {
		Optional<ProductEntity> optional = repository.findById(id);
		return optional.isPresent() ? Optional.of(mapper.toEntity(optional.get())) : Optional.empty();
	}

	@Override
	public Product save(Product product) {
		ProductEntity entity = repository.save(mapper.toJpaEntity(product));
		return mapper.toEntity(entity);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	public List<Product> findByCategoryId(Category category) {
		return repository.findByCategory(category).stream().map(mapper::toEntity).toList();
	}
	
	@Override
	public List<Product> findByIds(List<Long> ids) {
		List<ProductEntity> entities = repository.findAllById(ids);
		return entities.stream().map(mapper::toEntity).toList();
	}

}
