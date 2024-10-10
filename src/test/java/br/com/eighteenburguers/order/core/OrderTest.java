package br.com.eighteenburguers.order.core;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.exception.OrderInvalidFieldException;

@ExtendWith(MockitoExtension.class)
class OrderTest {

	@Test
	void shouldBeNotSetInvalidCustomer() {
		assertThrows(OrderInvalidFieldException.class, () -> new Order(null, List.of()));
	}
}
