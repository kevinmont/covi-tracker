package com.choza.pequenines.vscovid.rest.vos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.ToString;

@ToString
public class LocationReqVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private Double lat;
	
	@NotNull
	private Double lng;
	
	private String address;
	
	private String description;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
