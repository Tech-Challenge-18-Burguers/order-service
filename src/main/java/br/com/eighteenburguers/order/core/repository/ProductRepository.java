package br.com.eighteenburguers.order.core.repository;

import java.util.List;
import java.util.Optional;

import br.com.eighteenburguers.order.core.PageData;
import br.com.eighteenburguers.order.core.Pageable;
import br.com.eighteenburguers.order.core.entity.Category;
import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.valueobject.product.ProductFilter;

public interface ProductRepository {

	PageData<Product> findAll(ProductFilter filter, Pageable pageable);
	
	Optional<Product> findById(Long id);
	
	Product save(Product product);
	
	void delete(Long id);
	
	List<Product> findByCategoryId(Category category);
}
