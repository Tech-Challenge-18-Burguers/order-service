package br.com.eighteenburguers.order.core.usecase;

import br.com.eighteenburguers.order.core.PageData;
import br.com.eighteenburguers.order.core.Pageable;
import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.valueobject.product.ProductFilter;

public interface FindProductsUseCase {

	PageData<Product> execute(ProductFilter filter, Pageable pageable);
}
