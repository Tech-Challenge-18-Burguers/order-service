package br.com.eighteenburguers.order.mapper;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

import org.mapstruct.Mapper;

import br.com.eighteenburguers.order.adapter.request.ProductRequest;
import br.com.eighteenburguers.order.adapter.response.ProductResponse;
import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.valueobject.product.ProductFilter;
import br.com.eighteenburguers.order.infra.repository.entity.ProductEntity;

@Mapper(unmappedTargetPolicy = IGNORE, nullValueCheckStrategy = ALWAYS)
public interface ProductMapper {

	ProductResponse toResponse(Product product);
	
	Product toEntity(ProductRequest request);
	
	Product toEntity(ProductEntity entity);
	
	ProductEntity toJpaEntity(Product product);
	
	ProductEntity toJpaEntity(ProductFilter filter);
}
