package br.com.eighteenburguers.order.adapter.request;

import java.math.BigDecimal;

import br.com.eighteenburguers.order.core.entity.Category;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank
    private String name;

    private Category category;

    private BigDecimal price;

    @NotBlank
    private String description;

    @NotBlank
    private String image;
}
