package br.com.eighteenburguers.order.core.usecase.product;

import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.repository.ProductRepository;

public class CreateProductUseCaseImpl implements CreateProductUseCase {

	private final ProductRepository repository;
	
	public CreateProductUseCaseImpl(ProductRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Product execute(Product product) throws BusinessException {
		return repository.save(product);
	}
    
}
