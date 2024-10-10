package br.com.eighteenburguers.order.infra.repository.converters;

import br.com.eighteenburguers.order.core.entity.product.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryAttributeConverter implements AttributeConverter<Category, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Category attribute) {
		if(attribute == null) return null;
		return attribute.getCode();
	}

	@Override
	public Category convertToEntityAttribute(Integer dbData) {
		if(dbData == null) return null;
		return Category.ofCode(dbData);
	}

}
