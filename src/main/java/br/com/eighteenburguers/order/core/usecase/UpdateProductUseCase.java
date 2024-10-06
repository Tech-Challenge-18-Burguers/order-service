package br.com.eighteenburguers.order.core.usecase;

import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface UpdateProductUseCase {

	Product execute(Long id, Product product) throws BusinessException;
}
