package br.com.eighteenburguers.order.core.usecase;

import br.com.eighteenburguers.order.core.entity.Customer;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.exception.CustomerNotFound;
import br.com.eighteenburguers.order.core.repository.CustomerRepository;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class FindCustomerUseCaseImplTest {
    @Mock
    CustomerRepository repository;

    @Captor
    ArgumentCaptor<String> captor;

    Faker faker;

    @BeforeEach
    void setup() {
        this.faker = Faker.instance();
    }

    @Test
    void shouldBeFindCustomerById() throws BusinessException {
        final String document = faker.random().hex();

        Mockito.when(repository.findByDocumentNumber(Mockito.anyString())).thenReturn(Optional.of(mockCustomer(document)));

        FindCustomerUseCase usecase = new FindCustomerUseCaseImpl(repository);
        Customer customer = usecase.execute(document);

        assertNotNull(customer);
        assertEquals(document, customer.getDocument());

        Mockito.verify(repository, Mockito.times(1)).findByDocumentNumber(captor.capture());
        assertEquals(document, captor.getValue());
    }

    @Test
    void shouldBeNotFindBecauseNotFound() {

        final String document = faker.random().hex();

        Mockito.when(repository.findByDocumentNumber(Mockito.anyString())).thenReturn(Optional.empty());

        FindCustomerUseCase usecase = new FindCustomerUseCaseImpl(repository);
        assertThrows(CustomerNotFound.class, () -> usecase.execute(document));

        Mockito.verify(repository, Mockito.times(1)).findByDocumentNumber(captor.capture());
        assertEquals(document, captor.getValue());
    }

    Customer mockCustomer(String document) {
        Customer customer = new Customer(1L, document, faker.name().fullName(), "mockemail@gmail.com");
        return customer;
    }
}
