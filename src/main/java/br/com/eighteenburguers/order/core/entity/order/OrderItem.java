package br.com.eighteenburguers.order.core.entity.order;

import br.com.eighteenburguers.order.core.entity.product.Product;

public class OrderItem {
    
    private Product product;
    private Integer quantity;
    private String observation;
    
    public OrderItem(Product product, Integer quantity, String observation) {
        this.product = product;
        this.quantity = quantity;
        this.observation = observation;
    }

    public String getObservation() {
        return observation;
    }

    public Product getProduct() {
        return product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
}
