package br.com.eighteenburguers.order.controller;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.eighteenburguers.order.adapter.OrderMapper;
import br.com.eighteenburguers.order.adapter.request.OrderUpdateStatusRequest;
import br.com.eighteenburguers.order.adapter.response.OrderResponse;
import br.com.eighteenburguers.order.core.entity.order.Order;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.usecase.order.CheckoutOrderUseCase;
import br.com.eighteenburguers.order.core.usecase.order.CreateOrderUseCase;
import br.com.eighteenburguers.order.core.usecase.order.FindAllOrdersUseCase;
import br.com.eighteenburguers.order.core.usecase.order.FindOrderByIdUseCase;
import br.com.eighteenburguers.order.core.usecase.order.UpdateOrderStatus;
import br.com.eighteenburguers.order.core.valueobject.order.OrderFilter;
import br.com.eighteenburguers.order.core.valueobject.pagination.PageData;
import br.com.eighteenburguers.order.core.valueobject.pagination.Pageable;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

	@Mock
	CheckoutOrderUseCase checkoutOrderUseCase;
	@Mock
	CreateOrderUseCase createOrderUseCase;
	@Mock
	FindAllOrdersUseCase findAllOrdersUseCase;
	@Mock
	FindOrderByIdUseCase findOrderByIdUseCase;
	@Mock
	UpdateOrderStatus updateOrderStatus;

	OrderMapper mapper;

	OrderController controller;

	@BeforeEach
	void setup() {
		this.mapper = Mappers.getMapper(OrderMapper.class);
		this.controller = new OrderController(checkoutOrderUseCase, createOrderUseCase, findAllOrdersUseCase,
				findOrderByIdUseCase, updateOrderStatus, mapper);
	}
	
	@Test
	void shouldBeList() {
		Mockito.when(findAllOrdersUseCase.execute(Mockito.any(), Mockito.any())).thenReturn(mockPageData());
		PageData<OrderResponse> page = controller.list(mockFilter(), new Pageable());
		assertNotNull(page);
	}

	@Test
	void shouldBeFindById() throws BusinessException {
		Mockito.when(findOrderByIdUseCase.execute(Mockito.anyLong())).thenReturn(new Order());
		OrderResponse order = controller.findById(Mockito.anyLong());
		assertNotNull(order);
	}
	
	@Test
	void shouldBeCreate() throws BusinessException {
		Mockito.when(createOrderUseCase.execute(Mockito.anyString(), Mockito.anyList())).thenReturn(new Order());
		OrderResponse order = controller.create(UUID.randomUUID().toString(), List.of());
		assertNotNull(order);
	}
	
	@Test
	void shouldBeCheckout() throws BusinessException {
		controller.checkout(1L);
		Mockito.verify(checkoutOrderUseCase, Mockito.times(1)).execute(Mockito.anyLong());
	}
	
	@Test
	void shouldBeUpdate() throws BusinessException {
		Mockito.when(updateOrderStatus.execute(Mockito.anyLong(), Mockito.any())).thenReturn(new Order());
		OrderResponse order = controller.update(1L, new OrderUpdateStatusRequest());
		assertNotNull(order);
	}

	private OrderFilter mockFilter() {
		OrderFilter filter = new OrderFilter();
		filter.setCustomerId(UUID.randomUUID().toString());
		return filter;
	}
	
	private PageData<Order> mockPageData() {
		PageData<Order> page = new PageData<>();
		page.setData(List.of());
		return page;
	}
	
}
