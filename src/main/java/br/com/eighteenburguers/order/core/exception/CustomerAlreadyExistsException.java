package br.com.eighteenburguers.order.core.exception;

public class CustomerAlreadyExistsException extends BusinessException{
    
    public CustomerAlreadyExistsException() {
        super("CST001", "Customer Already Exists");
    }
}
