package com.choza.pequenines.vscovid.repositories.entities;

import java.io.Serializable;
import java.util.Objects;

public class FamilyEntitiePK implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long principal;
	private Long member;

	public FamilyEntitiePK() {
	}

	public FamilyEntitiePK(Long principal, Long member) {
		this.principal = principal;
		this.member = member;
	}

	public Long getPrincipal() {
		return principal;
	}

	public void setPrincipal(Long principal) {
		this.principal = principal;
	}

	public Long getMember() {
		return member;
	}

	public void setMember(Long member) {
		this.member = member;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		FamilyEntitiePK that = (FamilyEntitiePK) o;
		return member.equals(that.member) && principal.equals(that.principal);
	}

	@Override
	public int hashCode() {
		return Objects.hash(member, principal);
	}

}
