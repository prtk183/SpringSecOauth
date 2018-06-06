package com.ssecurity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="Authority")
public class Authority implements GrantedAuthority{

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Integer id;
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}

	String authority;
	
	
	public Authority()
	{
		
	}
	
	

	public Authority(String authority) {
		super();
		this.authority = authority;
	}



	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
