package br.com.eighteenburguers.order.core;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@ParameterObject
@NoArgsConstructor
public class Pageable {

	@Size(min = 1)
	@Parameter(example = "1", required = false, description = "Page number")
	private Integer page = 0;
	
	@Size(min = 1, max = 100)
	@Parameter(example = "10", required = false, description = "Size for each page")
	private Integer size = 25;

	@Setter
	@Parameter(example = "price.value", required = false, description = "Field sort data")
	private String sort;
	
	@Parameter(example = "ASC or DESC", required = false, description = "Field direction data")
	private String direction;
	
	public Pageable(Integer page, Integer size) {
		this.isValid("page", page);
		this.isValid("size", size);

		this.page = page - 1;
		this.size = size;
	}

	public void setPage(Integer page) {
		this.isValid("page", page);
		this.page = page - 1;
	}

	public void setSize(Integer size) {
		this.isValid("size", size);
		this.size = size;
	}
	
	public void setDirection(String direction) {
		if(!direction.equalsIgnoreCase("ASC") && !direction.equalsIgnoreCase("DESC")) {
			throw new IllegalArgumentException("Invalid direction property");
		}
		this.direction = direction;
	}

	private void isValid(String parameter, Integer value) {
		if (value < 1)
			throw new IllegalArgumentException(parameter + " connot be less than 0");
	}
	
	public PageRequest getPageRequest() {
		if(this.sort != null) {
			return PageRequest.of(this.page, this.size, Sort.by(this.sort));
		}
		return PageRequest.of(this.page, this.size);
	}
	
	public PageRequest getPageRequest(Sort sort) {
		return PageRequest.of(this.page, this.size, sort);
	}
	
	public PageRequest getPageRequest(String ...properties) {
		return PageRequest.of(this.page, this.size, Sort.by(properties));
	}
	
	public PageRequest getPageRequest(Direction direction) {
		if(this.sort != null) {
			return PageRequest.of(this.page, this.size, Sort.by(direction, this.sort));
		}
		return PageRequest.of(this.page, this.size, Sort.by(direction, "id"));
	}
	
	public PageRequest getPageRequest(Direction direction, String ...properties) {
		Sort sorted;
		Direction direct;
		
		if(this.direction != null && !this.direction.isEmpty()) {
			direct = Direction.fromString(this.direction);
		} else {
			direct = direction;
		}
		
		if(this.sort != null && !this.sort.isEmpty()) {
			sorted = Sort.by(direct, this.sort);
		} else {
			sorted = Sort.by(direct, properties);
		}
		return PageRequest.of(this.page, this.size, sorted);
	}
	
}