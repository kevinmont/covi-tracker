package com.choza.pequenines.vscovid.repositories.entities;

public enum HealthStatusEnum {
	SIN_CONFIRMAR("SIN_CONFIRMAR"), 
	CON_SINTOMAS("CON_SINTOMAS"),
	CONTAGIADO("CONTAGIADO"),
	NO_CONTAGIADO("NO_CONTAGIADO");
	

	private String status;

	private HealthStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
