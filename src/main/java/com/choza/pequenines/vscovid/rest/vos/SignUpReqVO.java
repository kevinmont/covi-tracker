package com.choza.pequenines.vscovid.rest.vos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.choza.pequenines.vscovid.repositories.entities.GenderEnum;
import com.choza.pequenines.vscovid.repositories.entities.HealthStatusEnum;

import lombok.ToString;

@ToString
public class SignUpReqVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String name;
	
	@NotNull
	private Short age;
	
	@NotNull
	private GenderEnum gender;
	
	@NotNull
	private HealthStatusEnum healthStatus;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public void setGender(GenderEnum gender) {
		this.gender = gender;
	}

	public HealthStatusEnum getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(HealthStatusEnum healthStatus) {
		this.healthStatus = healthStatus;
	}
}
