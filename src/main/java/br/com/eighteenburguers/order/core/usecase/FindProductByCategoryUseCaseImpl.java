package br.com.eighteenburguers.order.core.usecase;

import java.util.List;

import br.com.eighteenburguers.order.core.entity.Category;
import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.repository.ProductRepository;

public class FindProductByCategoryUseCaseImpl implements FindProductByCategoryUseCase {

	private final ProductRepository repository;

	public FindProductByCategoryUseCaseImpl(ProductRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<Product> execute(Integer code) throws BusinessException {
		Category category = Category.ofCode(code);
		return repository.findByCategoryId(category);
	}

}
