package br.com.eighteenburguers.order.infra.configuration.context.usecases;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.eighteenburguers.order.core.repository.ProductRepository;
import br.com.eighteenburguers.order.core.usecase.CreateProductUseCase;
import br.com.eighteenburguers.order.core.usecase.CreateProductUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.DeleteProductByIdUseCase;
import br.com.eighteenburguers.order.core.usecase.DeleteProductByIdUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.FindProductByCategoryUseCase;
import br.com.eighteenburguers.order.core.usecase.FindProductByCategoryUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.FindProductByIdUseCase;
import br.com.eighteenburguers.order.core.usecase.FindProductByIdUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.FindProductsUseCase;
import br.com.eighteenburguers.order.core.usecase.FindProductsUseCaseImpl;
import br.com.eighteenburguers.order.core.usecase.UpdateProductUseCase;
import br.com.eighteenburguers.order.core.usecase.UpdateProductUseCaseImpl;

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
