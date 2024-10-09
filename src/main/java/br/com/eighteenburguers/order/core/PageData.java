package br.com.eighteenburguers.order.core;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({ "data", "page", "size", "total", "hasNext", "hasPrev" })
public class PageData<T> {

	private List<T> data;
	private Long total;
	private Integer page;
	private Integer pageSize;
	private Boolean hasNext;
	private Boolean hasPrev;
	
	public void copyMetadata(Page<?> page) {
		this.page = (page.getNumber() + 1);
		this.hasNext = page.hasNext();
		this.hasPrev = page.hasPrevious();
		this.pageSize = page.getNumberOfElements();
		this.total = page.getTotalElements();
	}
	
	public void copyMetadata(PageData<?> data) {
		this.page = data.getPage();
		this.hasNext = data.getHasNext();
		this.hasPrev = data.getHasPrev();
		this.pageSize = data.getPage();
		this.total = data.getTotal();
	}
	
	public PageData(List<T> data, Page<?> page) {
		super();
		this.data = data;
		this.copyMetadata(page);
	}
	
	public static <E> PageData<E> clone(List<E> data, PageData<?> pageData) {
		@SuppressWarnings("unchecked")
		var pageable = (PageData<E>) PageData.builder()
			.hasNext(pageData.getHasNext())
			.hasPrev(pageData.getHasPrev())
			.page(pageData.getPage())
			.pageSize(pageData.getPageSize())
			.build();
		pageable.setData((List<E>) data);
		return pageable;
	}
	
	public static <E> PageData<E> from(Page<E> page) {
		List<E> data = page.getContent();
		PageData<E> pageData = new PageData<>();
		pageData.setData(data);
		pageData.setHasNext(page.hasNext());
		pageData.setHasPrev(page.hasPrevious());
		pageData.setPage(page.getNumber() + 1);
		pageData.setPageSize(page.getNumberOfElements());
		pageData.setTotal(page.getTotalElements());
		return pageData;
	}
	
	public static <T> PageData<T> from(List<T> data, Page<?> page) {
		return new PageData<>(data, page);
	}
	
}