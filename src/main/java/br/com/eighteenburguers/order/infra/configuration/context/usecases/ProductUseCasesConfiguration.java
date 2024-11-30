package br.com.eighteenburguers.order.infra.configuration.context.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.order.core.repository.ProductRepository;
import br.com.eighteenburguers.order.core.usecase.product.CreateProductUseCase;
import br.com.eighteenburguers.order.core.usecase.product.CreateProductUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.product.DeleteProductByIdUseCase;
import br.com.eighteenburguers.order.core.usecase.product.DeleteProductByIdUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.product.FindProductByCategoryUseCase;
import br.com.eighteenburguers.order.core.usecase.product.FindProductByCategoryUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.product.FindProductByIdUseCase;
import br.com.eighteenburguers.order.core.usecase.product.FindProductByIdUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.product.FindProductsUseCase;
import br.com.eighteenburguers.order.core.usecase.product.FindProductsUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.product.UpdateProductUseCase;
import br.com.eighteenburguers.order.core.usecase.product.UpdateProductUseCaseImpl;

@Configuration
public class ProductUseCasesConfiguration {

	@Bean
	CreateProductUseCase createProductUseCase(ProductRepository repository) {
		return new CreateProductUseCaseImpl(repository);
	}
	
	@Bean
	DeleteProductByIdUseCase deleteProductByIdUseCase(ProductRepository repository) {
		return new DeleteProductByIdUseCaseImpl(repository);
	}
	
	@Bean
	FindProductByCategoryUseCase findProductByCategoryUseCase(ProductRepository repository) {
		return new FindProductByCategoryUseCaseImpl(repository);
	}
	
	@Bean
	FindProductByIdUseCase findProductByIdUseCase(ProductRepository repository) {
		return new FindProductByIdUseCaseImpl(repository);
	}
	
	@Bean
	FindProductsUseCase findProductsUseCase(ProductRepository repository) {
		return new FindProductsUseCaseImpl(repository);
	}
	
	@Bean
	UpdateProductUseCase updateProductUseCase(ProductRepository repository) {
		return new UpdateProductUseCaseImpl(repository);
	}
}
