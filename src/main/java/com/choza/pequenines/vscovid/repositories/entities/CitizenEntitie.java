package com.choza.pequenines.vscovid.repositories.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.ToString;

@Entity
@Table(name = "COV02_CITIZEN")
@ToString
public class CitizenEntitie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "age")
	private Short age;

	@Enumerated(EnumType.STRING)
	@Column(name = "gender")
	private GenderEnum gender;

	@OneToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private UserEntitie user;

	@OneToOne
	@JoinColumn(name = "location_id", referencedColumnName = "id")
	private LocationEntitie location;

	@Column(name = "date_created")
	private Date dateCreated;

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

	public UserEntitie getUser() {
		return user;
	}

	public void setUser(UserEntitie user) {
		this.user = user;
	}

	public LocationEntitie getLocation() {
		return location;
	}

	public void setLocation(LocationEntitie location) {
		this.location = location;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

}
