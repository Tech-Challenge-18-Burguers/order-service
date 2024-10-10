package br.com.eighteenburguers.order.core.usecase.order;

import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface CheckoutOrderUseCase {
    
    void execute(Long orderId) throws BusinessException;
}
