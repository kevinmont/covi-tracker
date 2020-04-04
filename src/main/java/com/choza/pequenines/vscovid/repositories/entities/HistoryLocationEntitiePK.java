package com.choza.pequenines.vscovid.repositories.entities;

import java.io.Serializable;
import java.util.Objects;

public class HistoryLocationEntitiePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long location;
	private Long citizen;

	public HistoryLocationEntitiePK() {
	}

	public HistoryLocationEntitiePK(Long location, Long citizen) {
		this.location = location;
		this.citizen = citizen;
	}

	public Long getLocation() {
		return location;
	}

	public void setLocation(Long location) {
		this.location = location;
	}

	public Long getCitizen() {
		return citizen;
	}

	public void setCitizen(Long citizen) {
		this.citizen = citizen;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		HistoryLocationEntitiePK that = (HistoryLocationEntitiePK) o;
		return location.equals(that.location) && citizen.equals(that.citizen);
	}

	@Override
	public int hashCode() {
		return Objects.hash(location, citizen);
	}

}
