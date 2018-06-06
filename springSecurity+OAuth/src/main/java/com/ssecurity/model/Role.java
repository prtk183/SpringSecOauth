package com.ssecurity.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="role")
@Data
public class Role {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String role;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(name="role_authority", joinColumns=@JoinColumn(name="role_id"), inverseJoinColumns=@JoinColumn(name="authority_id"))
	private Set<Authority> authority;
	
	
	
	public Role(String role, Set<Authority> authority) {
		super();
		this.role = role;
		this.authority = authority;
	}



	public Set<Authority> getAuthority() {
		return authority;
	}



	public void setAuthority(Set<Authority> authority) {
		this.authority = authority;
	}



	public Role() {}
	

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
