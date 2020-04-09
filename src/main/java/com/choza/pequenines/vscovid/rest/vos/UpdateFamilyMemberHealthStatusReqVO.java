package com.choza.pequenines.vscovid.rest.vos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.ToString;

@ToString
public class UpdateFamilyMemberHealthStatusReqVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotNull
	private EditHealthStatusEnumUserReqVO healthStatus;

	public EditHealthStatusEnumUserReqVO getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(EditHealthStatusEnumUserReqVO healthStatus) {
		this.healthStatus = healthStatus;
	}
	
}
