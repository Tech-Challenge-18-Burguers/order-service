package br.com.eighteenburguers.order.api.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eighteenburguers.order.adapter.request.OrderRequest;
import br.com.eighteenburguers.order.adapter.response.OrderResponse;
import br.com.eighteenburguers.order.controller.OrderController;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.valueobject.order.OrderFilter;
import br.com.eighteenburguers.order.core.valueobject.pagination.PageData;
import br.com.eighteenburguers.order.core.valueobject.pagination.Pageable;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@AllArgsConstructor
@Tag(name = "Orders")
@RequestMapping("/orders")
public class OrderResource {

	private final OrderController controller;
	
	@GetMapping
    @ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = OrderResponse.class))))
    public ResponseEntity<?> list(OrderFilter filter, Pageable pageable) {
        PageData<OrderResponse> page = controller.list(filter, pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
	
	@GetMapping("/{id}")
	@ApiResponse(responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderResponse.class)))
	public ResponseEntity<?> findById(@PathVariable("id") Long orderId) throws BusinessException {
		OrderResponse order = controller.findById(orderId);
		return ResponseEntity.status(HttpStatus.OK).body(order);
	}
	
	@PostMapping
	@Transactional
	@ApiResponse(responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = OrderResponse.class)))
	public ResponseEntity<?> create(@RequestBody OrderRequest orderRequest) throws BusinessException {
		OrderResponse order = controller.create(orderRequest.getCustomerId(), orderRequest.getItems());
		return ResponseEntity.status(HttpStatus.CREATED).body(order);
	}

}
