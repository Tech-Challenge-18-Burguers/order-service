package br.com.eighteenburguers.order.core.usecase;

import br.com.eighteenburguers.order.core.entity.Customer;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.repository.CustomerRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class CreateCustomerUseCaseImplTest {

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
        CreateCustomerUseCase usecase = new CreateCustomerUseCaseImpl(repository);
        Mockito.when(repository.save(Mockito.any())).thenReturn(customer);
        Customer response = usecase.execute(customer);
        assertEquals(response.getName(), customer.getName());
    }

    Customer mockCustomer() {
        Customer customer = new Customer(2L, faker.random().hex(), faker.name().fullName(), "mock@gmail.com");
        return customer;
    }
}
