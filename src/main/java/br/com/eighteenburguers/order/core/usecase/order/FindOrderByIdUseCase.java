package br.com.eighteenburguers.order.core.usecase.order;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface FindOrderByIdUseCase {
    
    Order execute(Long orderId) throws BusinessException;
}
