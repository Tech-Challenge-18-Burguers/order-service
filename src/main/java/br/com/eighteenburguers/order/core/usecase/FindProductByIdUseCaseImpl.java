package br.com.eighteenburguers.order.core.usecase;

import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.ProductNotExistsException;
import br.com.eighteenburguers.order.core.repository.ProductRepository;

public class FindProductByIdUseCaseImpl implements FindProductByIdUseCase {

	private final ProductRepository repository;
	
	public FindProductByIdUseCaseImpl(ProductRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Product execute(Long id) throws BusinessException {
		return repository.findById(id).orElseThrow(ProductNotExistsException::new);
	}

}
