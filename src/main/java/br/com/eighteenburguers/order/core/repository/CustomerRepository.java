package br.com.eighteenburguers.order.core.repository;

import br.com.eighteenburguers.order.core.entity.Customer;

public interface CustomerRepository {

    public Customer findByDocumentNumber(String documentNumber);

    public Customer save(Customer customer);
}