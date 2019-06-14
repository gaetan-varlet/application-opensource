package com.example.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.Utilisateur;
import com.example.api.service.UtilisateurService;

@RestController
@RequestMapping("utilisateur")
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@GetMapping
	public List<Utilisateur> findUtilisateur() {
		return utilisateurService.findAll();
	}
	
}
