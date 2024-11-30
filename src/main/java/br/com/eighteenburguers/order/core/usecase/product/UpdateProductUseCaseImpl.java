package br.com.eighteenburguers.order.core.usecase.product;

import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.ProductNotExistsException;
import br.com.eighteenburguers.order.core.repository.ProductRepository;

public class UpdateProductUseCaseImpl implements UpdateProductUseCase {

	private final ProductRepository repository;

	public UpdateProductUseCaseImpl(ProductRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Product execute(final Long id, final Product product) throws BusinessException {
		Product persisted = repository.findById(id).orElseThrow(ProductNotExistsException::new);
		
		persisted.setCategory(product.getCategory());
		persisted.setDescription(product.getDescription());
		persisted.setImage(product.getImage());
		persisted.setName(product.getName());
		persisted.setPrice(product.getPrice());
		
		return repository.save(persisted);
	}

}
