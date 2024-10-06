package br.com.eighteenburguers.order.core.exception;

public class OrderNotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

	public OrderNotFoundException() {
        super("ONF001", "Order not found");
    }
    
}
