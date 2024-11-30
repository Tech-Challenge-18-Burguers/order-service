package br.com.eighteenburguers.order.core.exception;

public class InvalidOrderStatusException extends BusinessException {

	private static final long serialVersionUID = 1L;

	public InvalidOrderStatusException() {
		super("INORST", "Invalid Order Status");
	}

}
