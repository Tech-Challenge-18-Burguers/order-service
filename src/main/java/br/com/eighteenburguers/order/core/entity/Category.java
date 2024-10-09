package br.com.eighteenburguers.order.core.entity;

import java.util.stream.Stream;

public enum Category {

	LANCHE(1, "Lanche"), ACOMPANHAMENTO(2, "Acompanhamento"), BEBIDA(3, "Bebida"), SOBREMESA(4, "Sobremesa");

	private Integer code;
	private String description;

	private Category(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public Integer getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}

	public static Category ofCode(Integer code) {
		return Stream.of(Category.values()).filter(item -> item.getCode().equals(code)).findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
