package com.choza.pequenines.vscovid.rest.vos;

import java.io.Serializable;

public class AuthResVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
