package br.com.eighteenburguers.order.controller;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.eighteenburguers.order.adapter.request.ProductRequest;
import br.com.eighteenburguers.order.adapter.response.ProductResponse;
import br.com.eighteenburguers.order.core.PageData;
import br.com.eighteenburguers.order.core.Pageable;
import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;
import br.com.eighteenburguers.order.core.usecase.CreateProductUseCase;
import br.com.eighteenburguers.order.core.usecase.DeleteProductByIdUseCase;
import br.com.eighteenburguers.order.core.usecase.FindProductByCategoryUseCase;
import br.com.eighteenburguers.order.core.usecase.FindProductByIdUseCase;
import br.com.eighteenburguers.order.core.usecase.FindProductsUseCase;
import br.com.eighteenburguers.order.core.usecase.UpdateProductUseCase;
import br.com.eighteenburguers.order.core.valueobject.product.ProductFilter;
import br.com.eighteenburguers.order.mapper.ProductMapper;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ProductController {
	
	private final CreateProductUseCase createProductUseCase;
	private final FindProductsUseCase findProductsUseCase;
	private final FindProductByIdUseCase findProductByIdUseCase;
	private final FindProductByCategoryUseCase findProductByCategoryUseCase;
	private final UpdateProductUseCase updateProductUseCase;
	private final DeleteProductByIdUseCase deleteProductByIdUseCase;
	private final ProductMapper mapper;

	public PageData<ProductResponse> list(final ProductFilter filter, final Pageable pageable) throws BusinessException {
		PageData<Product> page = findProductsUseCase.execute(filter, pageable);
		List<ProductResponse> products = page.getData().stream().map(mapper::toResponse).toList();
		return PageData.clone(products, page);
	}
	
	public ProductResponse findById(final Long id) throws BusinessException {
		Product product = findProductByIdUseCase.execute(id);
		return mapper.toResponse(product);
	}
	
	public List<ProductResponse> findByCategoryId(final Integer categoryId) throws BusinessException {
		List<Product> list = findProductByCategoryUseCase.execute(categoryId);
		return list.stream().map(mapper::toResponse).toList();
	}
	
	public ProductResponse create(final ProductRequest request) throws BusinessException {
		Product product = createProductUseCase.execute(mapper.toEntity(request));
		return mapper.toResponse(product);
	}
	
	public ProductResponse update(final Long id, ProductRequest request) throws BusinessException {
		Product product = updateProductUseCase.execute(id, mapper.toEntity(request));
		return mapper.toResponse(product);
	}
	
	public void delete(final Long id) throws BusinessException {
		deleteProductByIdUseCase.execute(id);
	}
	
}
