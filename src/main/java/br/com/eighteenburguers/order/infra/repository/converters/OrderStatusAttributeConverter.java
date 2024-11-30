package br.com.eighteenburguers.order.infra.repository.converters;

import br.com.eighteenburguers.order.core.entity.order.OrderStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OrderStatusAttributeConverter implements AttributeConverter<OrderStatus, Integer> {

	@Override
	public Integer convertToDatabaseColumn(OrderStatus attribute) {
		if(attribute == null) return null;
		return attribute.getCode();
	}

	@Override
	public OrderStatus convertToEntityAttribute(Integer dbData) {
		if(dbData == null) return null;
		return OrderStatus.ofCode(dbData);
	}

	
}
