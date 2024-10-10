package br.com.eighteenburguers.order.core.usecase.product;

import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.valueobject.pagination.PageData;
import br.com.eighteenburguers.order.core.valueobject.pagination.Pageable;
import br.com.eighteenburguers.order.core.valueobject.product.ProductFilter;

public interface FindProductsUseCase {

	PageData<Product> execute(ProductFilter filter, Pageable pageable);
}
