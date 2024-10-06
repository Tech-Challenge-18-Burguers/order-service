package br.com.eighteenburguers.order.core.usecase;

import java.util.List;

import br.com.eighteenburguers.order.core.entity.Product;

public interface FindProductsUseCase {

	List<Product> execute();
}
