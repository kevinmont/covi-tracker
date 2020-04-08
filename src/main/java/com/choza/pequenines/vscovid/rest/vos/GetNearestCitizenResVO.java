package com.choza.pequenines.vscovid.rest.vos;

import java.io.Serializable;

import com.choza.pequenines.vscovid.repositories.entities.HealthStatusEnum;

public class GetNearestCitizenResVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HealthStatusEnum healthStatus;
	
	private LocationResVO location;

	public HealthStatusEnum getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(HealthStatusEnum healthStatus) {
		this.healthStatus = healthStatus;
	}

	public LocationResVO getLocation() {
		return location;
	}

	public void setLocation(LocationResVO location) {
		this.location = location;
	}
}
