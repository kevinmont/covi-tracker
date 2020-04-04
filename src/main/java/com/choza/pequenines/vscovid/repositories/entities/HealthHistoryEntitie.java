package com.choza.pequenines.vscovid.repositories.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.ToString;

@Entity
@Table(name = "COV06_HEALTH_HISTORY")
@ToString
public class HealthHistoryEntitie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "citizen_id", referencedColumnName = "id")
	private CitizenEntitie citizen;

	@ManyToOne
	@JoinColumn(name = "health_status_id", referencedColumnName = "id")
	private HealthStatusEntitie status;

	@Column(name = "date_creation")
	private Date dateCreation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public CitizenEntitie getCitizen() {
		return citizen;
	}

	public void setCitizen(CitizenEntitie citizen) {
		this.citizen = citizen;
	}

	public HealthStatusEntitie getStatus() {
		return status;
	}

	public void setStatus(HealthStatusEntitie status) {
		this.status = status;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

}
