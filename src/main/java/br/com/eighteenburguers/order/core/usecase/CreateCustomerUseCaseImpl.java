package br.com.eighteenburguers.order.core.usecase;

import br.com.eighteenburguers.order.core.entity.Customer;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.CustomerAlreadyExistsException;
import br.com.eighteenburguers.order.core.repository.CustomerRepository;

public class CreateCustomerUseCaseImpl implements CreateCustomerUseCase {

    private final CustomerRepository customerRepository;

    public CreateCustomerUseCaseImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer execute(Customer customer) throws BusinessException {
        var createdCustomer = customerRepository.findByDocumentNumber(customer.getDocument());

        if (createdCustomer != null) {
            throw new CustomerAlreadyExistsException();
        }

        return customerRepository.save(customer);
    }

}
