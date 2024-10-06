package br.com.eighteenburguers.order.core.exception;

public class ProductAlreadyExistsException extends BusinessException {

    private static final long serialVersionUID = 1L;

	public ProductAlreadyExistsException() {
        super("PRD001", "Produto já cadastrado!");
    }
}
