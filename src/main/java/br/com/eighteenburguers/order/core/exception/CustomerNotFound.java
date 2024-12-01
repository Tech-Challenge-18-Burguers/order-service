package br.com.eighteenburguers.order.core.exception;


public class CustomerNotFound extends BusinessException {
    
    public CustomerNotFound(){
        super("CNT", "Usuário não encontrado");
    }


}
