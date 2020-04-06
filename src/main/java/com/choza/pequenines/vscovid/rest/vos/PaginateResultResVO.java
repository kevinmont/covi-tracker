package com.choza.pequenines.vscovid.rest.vos;

import java.io.Serializable;
import java.util.List;

import lombok.ToString;

@ToString
public class PaginateResultResVO<E> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<E> result;
	
	private Integer page;
	
	private Integer totalPages;

	public List<E> getResult() {
		return result;
	}

	public void setResult(List<E> result) {
		this.result = result;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Integer totalPages) {
		this.totalPages = totalPages;
	}
	
}
