package br.com.eighteenburguers.order.adapter.response;

import java.math.BigDecimal;

import br.com.eighteenburguers.order.core.entity.product.Category;
import lombok.Data;

@Data
public class OrderItemResponse {
    
    private Long id;

    private String name;

    private Category category;

    private BigDecimal price;

    private Integer quantity;

    private String observation;
}
