package br.com.eighteenburguers.order.event;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.eighteenburguers.order.adapter.request.OrderMessage;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.usecase.order.UpdateOrderStatus;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrderEvent {

	private final UpdateOrderStatus updateOrderStatus;
	
	@Transactional
	@EventListener
	public void onUpdateStatus(OrderMessage message) throws BusinessException {
		updateOrderStatus.execute(message.getOrderId(), message.getStatus());
	}
}
