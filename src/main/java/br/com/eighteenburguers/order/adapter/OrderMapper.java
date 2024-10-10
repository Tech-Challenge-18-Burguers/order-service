package br.com.eighteenburguers.order.adapter;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.eighteenburguers.order.adapter.request.OrderItemRequest;
import br.com.eighteenburguers.order.adapter.request.OrderRequest;
import br.com.eighteenburguers.order.adapter.response.OrderItemResponse;
import br.com.eighteenburguers.order.adapter.response.OrderResponse;
import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.entity.order.OrderItem;
import br.com.eighteenburguers.order.core.valueobject.order.OrderFilter;
import br.com.eighteenburguers.order.infra.repository.entity.OrderEntity;
import br.com.eighteenburguers.order.infra.repository.entity.OrderItemEntity;

@Mapper(unmappedTargetPolicy = IGNORE, nullValueCheckStrategy = ALWAYS)
public interface OrderMapper {

	Order toEntity(OrderRequest request);
	
	@Mapping(target = "statusDescription", source = "status.description")
	OrderResponse toResponse(Order order);
	
	@Mapping(target = "product.id", source = "productId")
	@Mapping(target = "quantity", source = "quantity")
	OrderItem toItemEntity(OrderItemRequest request);

	OrderEntity toEntity(OrderFilter filter);

	@Mapping(target = "items", source = "items")
	Order toEntity(OrderEntity entity);

	@Mapping(target = "items", source = "items")
	OrderEntity toJpaEntity(Order order);
	
    @Mapping(target = "id", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "category", source = "product.category")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "quantity", source = "quantity")
    @Mapping(target = "observation", source = "observation")
    OrderItemResponse toResponse(OrderItem item);
    
    @Mapping(target = "productId", source = "product.id")
    @Mapping(target = "name", source = "product.name")
    @Mapping(target = "category", source = "product.category")
    @Mapping(target = "price", source = "product.price")
    @Mapping(target = "description", source = "product.description")
    @Mapping(target = "quantity", source = "quantity")
    OrderItemEntity toEntity(OrderItem item);
    
    @Mapping(target = "product.id", source = "id")
    @Mapping(target = "product.category", source = "category")
    @Mapping(target = "product.description", source = "description")
    @Mapping(target = "product.name", source = "name")
    @Mapping(target = "product.price", source = "price")
    OrderItem toOrderItem(OrderItemEntity entity);
}
