package br.com.eighteenburguers.order.core.usecase;

import java.util.List;

import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.repository.ProductRepository;

public class FindProductsUseCaseImpl implements FindProductsUseCase {

	private final ProductRepository repository;

	public FindProductsUseCaseImpl(ProductRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Product> execute() {
		return repository.findAll();
	}

}
