package br.com.eighteenburguers.order.core.usecase.product;

import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface FindProductByIdUseCase {

	Product execute(Long id) throws BusinessException;
}
