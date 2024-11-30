package br.com.eighteenburguers.order.core.repository;

import java.util.List;
import java.util.Optional;

import br.com.eighteenburguers.order.core.entity.product.Category;
import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.valueobject.pagination.PageData;
import br.com.eighteenburguers.order.core.valueobject.pagination.Pageable;
import br.com.eighteenburguers.order.core.valueobject.product.ProductFilter;

public interface ProductRepository {

	PageData<Product> findAll(ProductFilter filter, Pageable pageable);
	
	Optional<Product> findById(Long id);
	
	Product save(Product product);
	
	void delete(Long id);
	
	List<Product> findByCategoryId(Category category);

	List<Product> findByIds(List<Long> ids);
}
