package br.com.eighteenburguers.order.core.usecase.product;

import java.util.List;

import br.com.eighteenburguers.order.core.entity.product.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface FindProductByCategoryUseCase {

	List<Product> execute(Integer code) throws BusinessException;
}
