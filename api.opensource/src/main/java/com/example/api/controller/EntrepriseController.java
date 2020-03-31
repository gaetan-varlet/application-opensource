package com.example.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Entreprise;
import com.example.api.repository.EntrepriseRepository;

@RestController
@RequestMapping("entreprise")
public class EntrepriseController {
	
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	
	@GetMapping
	public List<Entreprise> findAll() {
		return entrepriseRepository.findAll();
	}
	
	@PostMapping
	public Entreprise create(@RequestBody Entreprise entreprise) {
		return entrepriseRepository.save(entreprise);
	}
	
}
