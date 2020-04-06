package com.choza.pequenines.vscovid.rest.vos;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.choza.pequenines.vscovid.repositories.entities.GenderEnum;
import com.choza.pequenines.vscovid.repositories.entities.HealthStatusEnum;

import lombok.ToString;

@ToString
public class AddFamilyMemberReqVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@NotEmpty
	private String name;
	
	@NotNull
	private Short age;
	
	@NotNull
	private GenderEnum gender;
	
	@NotNull
	private HealthStatusEnum healthStatus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Short getAge() {
		return age;
	}

	public void setAge(Short age) {
		this.age = age;
	}

	public GenderEnum getGender() {
		return gender;
	}

	public void setGender(GenderEnum sexo) {
		this.gender = sexo;
	}

	public HealthStatusEnum getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(HealthStatusEnum healthStatus) {
		this.healthStatus = healthStatus;
	}
}
