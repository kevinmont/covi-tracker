package com.choza.pequenines.vscovid.rest.vos;

import java.io.Serializable;

import com.choza.pequenines.vscovid.repositories.entities.GenderEnum;
import com.choza.pequenines.vscovid.repositories.entities.HealthStatusEnum;

import lombok.ToString;

@ToString
public class FamilyMemberResVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private String name;
	
	private Short age;
	
	private GenderEnum gender;
	
	private HealthStatusEnum healthStatus;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
