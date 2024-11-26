package br.com.eighteenburguers.order.core.usecase;


import br.com.eighteenburguers.order.core.entity.Customer;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.CustomerNotFound;
import br.com.eighteenburguers.order.core.repository.CustomerRepository;

import java.util.Optional;

public class FindCustomerUseCaseImpl implements FindCustomerUseCase {

    private CustomerRepository customerRepository;

    public FindCustomerUseCaseImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public Optional<Customer> execute(String document) throws BusinessException {
        var customer = customerRepository.findByDocumentNumber(document);

        if(customer == null){
            throw new CustomerNotFound();
        }

        return customerRepository.findByDocumentNumber(document);
    }
}