package br.com.eighteenburguers.order.core.usecase;

import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface FindProductByIdUseCase {

	Product execute(Long id) throws BusinessException;
}
