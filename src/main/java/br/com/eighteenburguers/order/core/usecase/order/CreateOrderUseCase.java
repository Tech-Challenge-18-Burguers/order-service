package br.com.eighteenburguers.order.core.usecase.order;

import java.util.List;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.entity.order.OrderItem;
import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface CreateOrderUseCase {
    
    Order execute(final String customerId, final List<OrderItem> items) throws BusinessException;
}
