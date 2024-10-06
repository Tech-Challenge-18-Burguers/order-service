package br.com.eighteenburguers.order.core.usecase;

import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.ProductNotExistsException;
import br.com.eighteenburguers.order.core.repository.ProductRepository;

public class DeleteProductByIdUseCaseImpl implements DeleteProductByIdUseCase {

	private final ProductRepository repository;
	
	public DeleteProductByIdUseCaseImpl(ProductRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public void execute(Long id) throws BusinessException {
		Product product = repository.findById(id).orElseThrow(ProductNotExistsException::new);
		repository.delete(product.getId());
	}

}
