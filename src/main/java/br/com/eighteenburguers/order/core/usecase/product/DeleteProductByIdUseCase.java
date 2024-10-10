package br.com.eighteenburguers.order.core.usecase.product;

import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface DeleteProductByIdUseCase {

    void execute(Long id) throws BusinessException;
}
