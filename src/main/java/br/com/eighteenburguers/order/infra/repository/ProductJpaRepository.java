package br.com.eighteenburguers.order.infra.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.eighteenburguers.order.core.entity.product.Category;
import br.com.eighteenburguers.order.infra.repository.entity.ProductEntity;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

	List<ProductEntity> findByCategory(Category category);
}
