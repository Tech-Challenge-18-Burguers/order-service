package br.com.eighteenburguers.order.core.usecase;

import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface DeleteProductByIdUseCase {

    void execute(Long id) throws BusinessException;
}
