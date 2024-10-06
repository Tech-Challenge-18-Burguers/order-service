package br.com.eighteenburguers.order.core.exception;

public class ProductNotExistsException extends BusinessException {

    private static final long serialVersionUID = 1L;

	public ProductNotExistsException() { super("PRD002", "Produto não cadastrado!"); }
}
