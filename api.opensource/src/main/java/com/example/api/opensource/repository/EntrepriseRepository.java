package com.example.api.opensource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.opensource.model.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, String> {
	
}
