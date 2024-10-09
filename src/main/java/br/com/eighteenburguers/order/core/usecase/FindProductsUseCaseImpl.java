package br.com.eighteenburguers.order.core.usecase;

import br.com.eighteenburguers.order.core.PageData;
import br.com.eighteenburguers.order.core.Pageable;
import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.repository.ProductRepository;
import br.com.eighteenburguers.order.core.valueobject.product.ProductFilter;

public class FindProductsUseCaseImpl implements FindProductsUseCase {

	private final ProductRepository repository;

	public FindProductsUseCaseImpl(ProductRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public PageData<Product> execute(ProductFilter filter, Pageable pageable) {
		return repository.findAll(filter, pageable);
	}

}
