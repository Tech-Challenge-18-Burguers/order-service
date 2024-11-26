package br.com.eighteenburguers.order.core.repository;

import br.com.eighteenburguers.order.core.entity.Customer;

import java.util.Optional;

public interface CustomerRepository {

    public Optional<Customer> findByDocumentNumber(String documentNumber);

    public Customer save(Customer customer);
}