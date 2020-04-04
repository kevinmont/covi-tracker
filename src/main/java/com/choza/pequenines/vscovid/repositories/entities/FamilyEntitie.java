package com.choza.pequenines.vscovid.repositories.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.ToString;

@Entity
@Table(name = "COV04_FAMILY")
@IdClass(FamilyEntitiePK.class)
@ToString
public class FamilyEntitie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "citizen_principal_id", referencedColumnName = "id")
	private CitizenEntitie principal;

	@Id
	@ManyToOne
	@JoinColumn(name = "citizen_red_id", referencedColumnName = "id")
	private CitizenEntitie member;

	public CitizenEntitie getPrincipal() {
		return principal;
	}

	public void setPrincipal(CitizenEntitie principal) {
		this.principal = principal;
	}

	public CitizenEntitie getMember() {
		return member;
	}

	public void setMember(CitizenEntitie member) {
		this.member = member;
	}

}
