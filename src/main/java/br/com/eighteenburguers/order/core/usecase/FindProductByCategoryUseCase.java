package br.com.eighteenburguers.order.core.usecase;

import java.util.List;

import br.com.eighteenburguers.order.core.entity.Product;
import br.com.eighteenburguers.order.core.exception.BusinessException;

public interface FindProductByCategoryUseCase {

	List<Product> execute(Integer code) throws BusinessException;
}
