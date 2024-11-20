package br.com.eighteenburguers.order.adapter.request;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

	private Long orderId;
	private BigDecimal amount;
}
