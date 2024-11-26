package br.com.eighteenburguers.order.core.usecase;


import br.com.eighteenburguers.order.core.entity.Customer;
import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface CreateCustomerUseCase {
    
    Customer execute(Customer customer) throws BusinessException;
}
