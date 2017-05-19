package com.biz.gbck.vo.spring;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;

/**
 * Created by defei on 5/18/17.
 */
public class PageVO<T> extends PageImpl<T> {

	private List<T> content = Collections.emptyList();

	private Boolean last = true;

	private Integer totalPages = 0;

	private Long totalElements = 0L;

	private Boolean fetchDataFromSuper = false;

	private Integer size = 0;

	private Integer number = 0;

	private Boolean first = true;

	private Integer numberOfElements = 0;

	public PageVO(){

		super(Collections.<T>emptyList(), null, 0);
		fetchDataFromSuper = false;
	}

	public PageVO(Page<T> page){

		super(page.getContent(),  new PageRequest(page.getNumber(), page.getSize()), page.getTotalElements());
		fetchDataFromSuper = true;
	}

	public void setContent(List<T> content){

		this.content = content;
	}

	@Override
	public List<T> getContent() {

		return fetchDataFromSuper ? super.getContent() : content;
	}

	@Override
	public boolean isLast() {

		return fetchDataFromSuper ? super.isLast() : last;
	}

	/**
	 * {@linkplain PageVO#last}
	 */
	public void setLast(Boolean last) {

		this.last = last;
	}

	@Override
	public int getTotalPages() {

		return fetchDataFromSuper ? super.getTotalPages() : totalPages;
	}

	/**
	 * {@linkplain PageVO#totalPages}
	 */
	public void setTotalPages(Integer totalPages) {

		this.totalPages = totalPages;
	}

	@Override
	public long getTotalElements() {

		return fetchDataFromSuper ? super.getTotalElements() : totalElements;
	}

	/**
	 * {@linkplain PageVO#totalElements}
	 */
	public void setTotalElements(Long totalElements) {

		this.totalElements = totalElements;
	}

	@Override
	public int getSize() {

		return fetchDataFromSuper ? super.getSize() : size;
	}

	/**
	 * {@linkplain PageVO#size}
	 */
	public void setSize(Integer size) {

		this.size = size;
	}

	@Override
	public int getNumber() {

		return fetchDataFromSuper ? super.getNumber() : number;
	}

	/**
	 * {@linkplain PageVO#number}
	 */
	public void setNumber(Integer number) {

		this.number = number;
	}

	@Override
	public boolean isFirst() {

		return fetchDataFromSuper ? super.isFirst() : first;
	}

	/**
	 * {@linkplain PageVO#first}
	 */
	public void setFirst(Boolean first) {

		this.first = first;
	}

	@Override
	public int getNumberOfElements() {

		return fetchDataFromSuper ? super.getNumberOfElements() : numberOfElements;
	}

	/**
	 * {@linkplain PageVO#numberOfElements}
	 */
	public void setNumberOfElements(Integer numberOfElements) {

		this.numberOfElements = numberOfElements;
	}
}
