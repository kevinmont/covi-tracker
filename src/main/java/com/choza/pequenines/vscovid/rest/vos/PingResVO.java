package com.choza.pequenines.vscovid.rest.vos;

import java.io.Serializable;

import lombok.ToString;

@ToString
public class PingResVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
