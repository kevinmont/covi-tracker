package com.choza.pequenines.vscovid.rest.vos;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.choza.pequenines.vscovid.repositories.entities.GenderEnum;

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
	@Valid
	private EditHealthStatusEnumUserReqVO healthStatus;

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

	public EditHealthStatusEnumUserReqVO getHealthStatus() {
		return healthStatus;
	}

	public void setHealthStatus(EditHealthStatusEnumUserReqVO healthStatus) {
		this.healthStatus = healthStatus;
	}
}
