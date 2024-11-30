package br.com.eighteenburguers.order.core.usecase;


import br.com.eighteenburguers.order.core.entity.Customer;
import br.com.eighteenburguers.order.core.exception.BusinessException;


public interface FindCustomerUseCase {

    Customer execute(String document) throws BusinessException;
}
