package br.com.eighteenburguers.order.adapter.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

import br.com.eighteenburguers.order.core.entity.Category;

@Data
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private Category category;

    private BigDecimal price;

    private String description;

    private String image;
}
