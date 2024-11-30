package br.com.eighteenburguers.order.core.valueobject;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import br.com.eighteenburguers.order.core.valueobject.pagination.PageData;

@ExtendWith(MockitoExtension.class)
class PageDataTest {
	
	@Mock
	Page<?> page;

	@Test
	void shouldBeCopy() {
		PageData<String> page = new PageData<String>();
		PageData<String> page2 = new PageData<String>();
		page2.copyMetadata(page);
		assertNotNull(page);
	}
	
	@Test
	void shouldBeFrom() {
		Page<String> pag = new PageImpl<String>(List.of());
		PageData<String> page = PageData.from(pag);
		assertNotNull(page);
	}
}
