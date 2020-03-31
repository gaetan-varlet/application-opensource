package com.example.api.model;

public class Utilisateur {
	
	private String idep;
	private String nom;
	
	public Utilisateur() {
	}
	
	public Utilisateur(String idep, String nom) {
		this.idep = idep;
		this.nom = nom;
	}
	
	public String getIdep() {
		return idep;
	}
	
	public void setIdep(String idep) {
		this.idep = idep;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
}
