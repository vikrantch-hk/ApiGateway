package com.hk.gateway.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * @author adlakha.vaibhav
 */
@Entity
@Table(name = "permission")
public class Permission implements Serializable {

	@Id
	@Column(name = "name")
	private String name;

	@ManyToMany(mappedBy = "permissions")
	private Set<Role> roles = new HashSet<Role>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Permission))
			return false;

		Permission that = (Permission) o;

		if (!name.equals(that.name))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}
}
