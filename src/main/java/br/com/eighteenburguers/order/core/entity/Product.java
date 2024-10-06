package br.com.eighteenburguers.order.core.entity;

import java.math.BigDecimal;

public class Product {

	private Long id;
	private String name;
	private Category category;
	private BigDecimal price;
	private String description;
	private String image;

	public Product(String name, Category category, BigDecimal price, String description, String image) {
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
		this.image = image;
	}
	
	public Product() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
}
