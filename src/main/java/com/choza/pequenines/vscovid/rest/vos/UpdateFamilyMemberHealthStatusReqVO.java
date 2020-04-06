package com.choza.pequenines.vscovid.rest.vos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.choza.pequenines.vscovid.repositories.entities.HealthStatusEnum;

import lombok.ToString;

@ToString
public class UpdateFamilyMemberHealthStatusReqVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private HealthStatusEnum healthStatus;

	public HealthStatusEnum getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(HealthStatusEnum healthStatus) {
		this.healthStatus = healthStatus;
	}
	
}
