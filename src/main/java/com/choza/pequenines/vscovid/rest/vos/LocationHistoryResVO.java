package com.choza.pequenines.vscovid.rest.vos;

import java.io.Serializable;
import java.util.Date;

import lombok.ToString;

@ToString
public class LocationHistoryResVO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Double lat;
	
	private Double lng;
	
	private Date date;

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
