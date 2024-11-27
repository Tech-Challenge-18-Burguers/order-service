package br.com.eighteenburguers.order.core.usecase;


import br.com.eighteenburguers.order.core.entity.Customer;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.CustomerNotFound;
import br.com.eighteenburguers.order.core.repository.CustomerRepository;

import java.util.Objects;
import java.util.Optional;

public class FindCustomerUseCaseImpl implements FindCustomerUseCase {

    private final CustomerRepository customerRepository;

    public FindCustomerUseCaseImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer execute(String document) throws BusinessException {
        var customer = customerRepository.findByDocumentNumber(document).orElse(null);

        if(Objects.isNull(customer)){
            throw new CustomerNotFound();
        }

        return customer;
    }
}