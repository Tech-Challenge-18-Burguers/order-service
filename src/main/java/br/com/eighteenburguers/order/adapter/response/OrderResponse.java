package br.com.eighteenburguers.order.adapter.response;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import br.com.eighteenburguers.order.core.entity.order.OrderStatus;
import lombok.Data;

@Data
public class OrderResponse {
    
    private Long id;
    private String customerId;
    private BigDecimal amount;
    private OrderStatus status;
    private String statusDescription;
    private List<OrderItemResponse> items;
    private Instant createdAt;
    private Instant updatedAt;

}
