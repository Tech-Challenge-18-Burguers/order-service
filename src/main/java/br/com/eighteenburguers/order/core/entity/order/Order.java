package br.com.eighteenburguers.order.core.entity.order;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import br.com.eighteenburguers.order.core.exception.OrderInvalidFieldException;

public class Order {
    
    private Long id;
    private String customerId;
    private BigDecimal amount = BigDecimal.ZERO;
    private OrderStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private List<OrderItem> items;

    public Order(Long id, String customerId, List<OrderItem> items) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
    }

    public Order(String customerId, List<OrderItem> items) {
        this.items = items;

        if(customerId == null){
            throw new OrderInvalidFieldException("customerId");
        }

        this.customerId = customerId;
    }

    public Order(Long id, String customerId, List<OrderItem> items, BigDecimal amount, OrderStatus status,
            Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.customerId = customerId;
        this.items = items;
        this.amount = amount;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    
    public Order() {
		
	}

    public void calculateAmount() {
        this.amount = this.items.stream()
            .map(item -> item.getProduct().getPrice().multiply(new BigDecimal(item.getQuantity())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}

	public Instant getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Instant updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<OrderItem> getItems() {
		return items;
	}

	public void setItems(List<OrderItem> items) {
		this.items = items;
	}
    
}
