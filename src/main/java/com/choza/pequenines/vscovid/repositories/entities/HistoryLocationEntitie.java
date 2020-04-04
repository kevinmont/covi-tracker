package com.choza.pequenines.vscovid.repositories.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.ToString;

@Entity
@Table(name = "COV07_HISTORY_LOCATION")
@IdClass(HistoryLocationEntitiePK.class)
@ToString
public class HistoryLocationEntitie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private LocationEntitie location;

	@Id
	@ManyToOne
	@JoinColumn(name = "citizen_id", referencedColumnName = "id")
	private CitizenEntitie citizen;

	@Column(name = "date_creation")
	private Date dateCreation;

	public LocationEntitie getLocation() {
		return location;
	}

	public void setLocation(LocationEntitie location) {
		this.location = location;
	}

	public CitizenEntitie getCitizen() {
		return citizen;
	}

	public void setCitizen(CitizenEntitie citizen) {
		this.citizen = citizen;
	}

	public Date getDateCreation() {
		return dateCreation;
	}

	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}

}
