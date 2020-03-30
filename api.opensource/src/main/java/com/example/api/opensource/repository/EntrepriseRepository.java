package com.example.api.opensource.repository;

import com.example.api.opensource.model.Entreprise;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrepriseRepository extends JpaRepository<Entreprise, String> {
	
}
