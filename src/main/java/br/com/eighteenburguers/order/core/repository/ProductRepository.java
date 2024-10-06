package br.com.eighteenburguers.order.core.repository;

import java.util.List;
import java.util.Optional;

import br.com.eighteenburguers.order.core.entity.Category;
import br.com.eighteenburguers.order.core.entity.Product;

public interface ProductRepository {

	List<Product> findAll();
	
	Optional<Product> findById(Long id);
	
	Product save(Product product);
	
	void delete(Long id);
	
	List<Product> findByCategoryId(Category category);
}
