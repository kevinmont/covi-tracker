package com.choza.pequenines.vscovid.repositories.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.ToString;

@Entity
@Table(name = "COV05_HEALTH_STATUS")
@ToString
public class HealthStatusEntitie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Short id;

	@Enumerated(EnumType.STRING)
	@Column(name = "status")
	private HealthStatusEnum status;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public HealthStatusEnum getStatus() {
		return status;
	}

	public void setStatus(HealthStatusEnum status) {
		this.status = status;
	}

}
