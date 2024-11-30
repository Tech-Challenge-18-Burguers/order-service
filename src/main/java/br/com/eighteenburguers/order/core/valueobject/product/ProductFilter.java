package br.com.eighteenburguers.order.core.valueobject.product;

import java.math.BigDecimal;

import br.com.eighteenburguers.order.core.entity.product.Category;

public class ProductFilter {

	private String name;
	private String description;
	private BigDecimal price;
	private Category category;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
