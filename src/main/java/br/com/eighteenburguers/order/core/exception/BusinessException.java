package br.com.eighteenburguers.order.core.exception;

public class BusinessException extends Exception {

    private static final long serialVersionUID = 1L;
	private String code;
    private String message;
    
    protected BusinessException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
    
}
