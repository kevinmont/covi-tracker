package com.choza.pequenines.vscovid.repositories.entities;

public enum HealthStatusEnum {
	NO_CONTAGIADO("NO_CONTAGIADO"), 
	PROPENSO_DE_CONTAGIO("PROPENSO_DE_CONTAGIO"),
	POSIBILIDAD_DE_CONTAGIO("POSIBILIDAD_DE_CONTAGIO"),
	CONTAGIADO("CONTAGIADO");
	

	private String status;

	private HealthStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
