package br.com.eighteenburguers.order.core.usecase.order;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.entity.order.OrderStatus;
import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface UpdateOrderStatus {

	Order execute(Long orderId, OrderStatus status) throws BusinessException;
}
