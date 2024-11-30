package br.com.eighteenburguers.order.core.exception;

public class OrderInvalidFieldException extends RuntimeException {

    private static final long serialVersionUID = 1L;
	private String fieldError;

    public OrderInvalidFieldException (String fieldError){
        this.fieldError = fieldError;
    }

    @Override
    public String getMessage() {
        return new StringBuilder().append("The field ").append(fieldError).append("must not be empty or null").toString();
    }
}
