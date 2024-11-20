package br.com.eighteenburguers.order.adapter.request;

import br.com.eighteenburguers.order.core.entity.order.OrderStatus;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderMessage {

	private Long orderId;
	private OrderStatus status;
}
