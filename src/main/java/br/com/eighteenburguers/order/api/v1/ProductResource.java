package br.com.eighteenburguers.order.api.v1;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eighteenburguers.order.adapter.request.ProductRequest;
import br.com.eighteenburguers.order.adapter.response.ProductResponse;
import br.com.eighteenburguers.order.controller.ProductController;
import br.com.eighteenburguers.order.core.PageData;
import br.com.eighteenburguers.order.core.Pageable;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.valueobject.product.ProductFilter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductResource {

	private final ProductController controller;
	
	@GetMapping
	@ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class))))
	public ResponseEntity<PageData<?>> list(ProductFilter filter, Pageable pageable) throws BusinessException {
		PageData<?> response = controller.list(filter, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/{id}")
	@ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class)))
	public ResponseEntity<?> findById(@PathVariable("id") final Long id) throws BusinessException {
		ProductResponse response = controller.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@GetMapping("/category/{categoryId}")
	@ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = ProductResponse.class))))
	public ResponseEntity<?> findByCategoryId(@PathVariable("categoryId") final Integer categoryId) throws BusinessException {
		List<ProductResponse> response = controller.findByCategoryId(categoryId);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> create(@RequestBody final ProductRequest request) throws BusinessException {
		ProductResponse response = controller.create(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@Transactional
	@PutMapping("/{id}")
	@ApiResponse(responseCode = "200", content = @Content(mediaType = APPLICATION_JSON_VALUE))
	public ResponseEntity<?> update(@PathVariable("id") final Long id, @RequestBody ProductRequest request) throws BusinessException {
		ProductResponse response = controller.update(id, request);
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@Transactional
	@DeleteMapping("/{id}")
	@ApiResponse(responseCode = "204", content = @Content(mediaType = APPLICATION_JSON_VALUE))
	public ResponseEntity<?> delete(@PathVariable("id") final Long id) throws BusinessException {
		controller.delete(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
