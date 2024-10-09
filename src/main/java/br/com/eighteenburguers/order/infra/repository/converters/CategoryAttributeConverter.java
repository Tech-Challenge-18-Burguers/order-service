package br.com.eighteenburguers.order.infra.repository.converters;

import br.com.eighteenburguers.order.core.entity.Category;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CategoryAttributeConverter implements AttributeConverter<Category, Integer> {

	@Override
	public Integer convertToDatabaseColumn(Category attribute) {
		return attribute.getCode();
	}

	@Override
	public Category convertToEntityAttribute(Integer dbData) {
		return Category.ofCode(dbData);
	}

}
