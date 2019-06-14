package com.example.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.api.model.Entreprise;

public interface EntrepriseRepository extends JpaRepository<Entreprise, String> {
	
}
