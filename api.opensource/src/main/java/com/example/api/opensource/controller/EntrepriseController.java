package com.example.api.opensource.controller;

import java.util.List;

import com.example.api.opensource.model.Entreprise;
import com.example.api.opensource.repository.EntrepriseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
