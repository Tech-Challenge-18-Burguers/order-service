package br.com.eighteenburguers.order.core.valueobject;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import br.com.eighteenburguers.order.core.valueobject.pagination.Pageable;

@ExtendWith(MockitoExtension.class)
class PageableTest {

	@Test
	void shouldCreatePageable01() {
		
		Pageable pageable = new Pageable();
		pageable.setPage(1);
		pageable.setSize(10);
		
		assertNotNull(pageable.getPageRequest());
		
		pageable.setSort("id");
		assertNotNull(pageable.getPageRequest());
	}
	
	@Test
	void shouldCreatePageable02() {
		
		Pageable pageable = new Pageable();
		pageable.setPage(1);
		pageable.setSize(10);
		
		assertNotNull(pageable.getPageRequest("id"));
	}
	
	@Test
	void shouldCreatePageable03() {
		
		Pageable pageable = new Pageable();
		pageable.setPage(1);
		pageable.setSize(10);
		pageable.setSort("id");
		
		assertNotNull(pageable.getPageRequest(Direction.ASC));
		
		pageable.setSort(null);
		assertNotNull(pageable.getPageRequest(Direction.ASC));
	}
	
	@Test
	void shouldCreatePageable04() {
		
		Pageable pageable = new Pageable();
		pageable.setPage(1);
		pageable.setSize(10);
		
		assertNotNull(pageable.getPageRequest(Sort.by("id")));
		
		pageable.setSort("id");
		assertNotNull(pageable.getPageRequest(Sort.by("id")));
	}
	
	@Test
	void shouldCreatePageable05() {
		Pageable pageable = new Pageable(1, 10);
		assertNotNull(pageable.getPageRequest(Direction.ASC, "id"));
	}
	
	@Test
	void shouldCreatePageable06() {
		Pageable pageable = new Pageable(1, 10);
		assertThrows(IllegalArgumentException.class, () -> pageable.setDirection("x"));
	}
	
	@Test
	void shouldCreatePageable07() {
		Pageable pageable = new Pageable(1, 10);
		pageable.setDirection("desc");
		pageable.setSort("id");
		assertNotNull(pageable.getPageRequest(Direction.ASC, "id"));
	}
	
	@Test
	void shouldCreatePageable08() {
		assertThrows(IllegalArgumentException.class, () -> new Pageable(-1, 10));
	}
	
}