package br.com.eighteenburguers.order.adapter.request;

import br.com.eighteenburguers.order.core.entity.order.OrderStatus;
import lombok.Data;

@Data
public class OrderUpdateStatusRequest {

	private OrderStatus status;
}
