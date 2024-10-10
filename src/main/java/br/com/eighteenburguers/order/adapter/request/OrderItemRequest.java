package br.com.eighteenburguers.order.adapter.request;

import lombok.Data;

@Data
public class OrderItemRequest {
    
    private Long productId;
    private Integer quantity;
    private String observation;
}
