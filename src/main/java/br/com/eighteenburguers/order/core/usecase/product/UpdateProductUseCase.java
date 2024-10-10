package br.com.eighteenburguers.order.core.usecase.product;

import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface UpdateProductUseCase {

	Product execute(Long id, Product product) throws BusinessException;
}
