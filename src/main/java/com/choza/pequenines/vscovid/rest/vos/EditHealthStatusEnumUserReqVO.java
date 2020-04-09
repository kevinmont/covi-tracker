package com.choza.pequenines.vscovid.rest.vos;

import lombok.ToString;

@ToString
public enum EditHealthStatusEnumUserReqVO {
	SIN_CONFIRMAR("SIN_CONFIRMAR"), 
	CON_SINTOMAS("CON_SINTOMAS");
	
	private String status;

	private EditHealthStatusEnumUserReqVO(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}
}
