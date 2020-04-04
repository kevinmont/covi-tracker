package com.choza.pequenines.vscovid.repositories.entities;

public enum HealthStatusEnum {
	NO_CONTAGIADO("NO CONTAGIADO"), 
	PROPENSO_DE_CONTAGIO("PROPENSO DE CONTAGIO"),
	POSIBILIDAD_DE_CONTAGIO("POSIBILIDAD DE CONTAGIO"),
	CONTAGIADO("CONTAGIADO");
	

	private String status;

	private HealthStatusEnum(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

}
