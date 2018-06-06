package com.ssecurity.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

@RestController
public class myuserController {

	@GetMapping("/unsecured")
	public ExampleClass getUnsecuredResponse() {
		return new ExampleClass("Maharshatra", "India");
	}
	
	@GetMapping("/secured")
	public ExampleClass getSecuredResponse() {
		return new ExampleClass("Maharshatra secured", "India secured");
		
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ExampleClass getAdminRes() {
		return new ExampleClass("ADMIN", "YYYY");
		
	}
	
	@GetMapping("/author1")
	@PreAuthorize("hasRole('ADMIN') and hasAuthority('ROLE_VIEW')")
	public ExampleClass getAuthorityRes() {
		return new ExampleClass("Auhorised to view", "YYYY");
		
	}
	
	@GetMapping("/author2")
	@PreAuthorize("hasAuthority('ROLE_VIEW')")
	public ExampleClass getAuthorityResp() {
		return new ExampleClass("Auhorised to ", "YYYY");
		
	}
}

@Data
class ExampleClass {
	private String field1;
	private String field2;
	public ExampleClass(String field1, String field2) {
		super();
		this.field1 = field1;
		this.field2 = field2;
	}
	public String getField1() {
		return field1;
	}
	public void setField1(String field1) {
		this.field1 = field1;
	}
	public String getField2() {
		return field2;
	}
	public void setField2(String field2) {
		this.field2 = field2;
	}
	
	
}