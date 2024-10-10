package br.com.eighteenburguers.order.core.usecase.product;

import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface CreateProductUseCase {

    Product execute(Product product) throws BusinessException;
}
