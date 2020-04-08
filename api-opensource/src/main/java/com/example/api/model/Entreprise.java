package com.example.api.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Entreprise {
	
	@Id
	private String siren;
	private String rs;
	
	public String getSiren() {
		return siren;
	}
	
	public void setSiren(String siren) {
		this.siren = siren;
	}
	
	public String getRs() {
		return rs;
	}
	
	public void setRs(String rs) {
		this.rs = rs;
	}
	
}
