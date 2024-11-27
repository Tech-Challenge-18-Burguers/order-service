package br.com.eighteenburguers.order.core.usecase;

import br.com.eighteenburguers.order.core.entity.Customer;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.repository.CustomerRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreateCustomerUseCaseImplTest {
    @Mock
    CustomerRepository repository;

    Faker faker;

    @BeforeEach
    void setup() {
        this.faker = Faker.instance();
    }

    @Test
    void shouldBeCreateNewCustomer() throws BusinessException {
        Customer customer = mockCustomer();
        Mockito.when(repository.save(Mockito.any())).thenReturn(customer);
        CreateCustomerUseCase usecase = new CreateCustomerUseCaseImpl(repository);
        Customer response = usecase.execute(customer);
        assertEquals(response.getName(), customer.getName());
    }

    Customer mockCustomer() {
        Customer customer = new Customer(1L, faker.random().hex(), faker.name().fullName(), "mockemail@gmail.com");
        return customer;
    }
}
