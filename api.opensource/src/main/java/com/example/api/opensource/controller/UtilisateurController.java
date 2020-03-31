package com.example.api.opensource.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;

import com.example.api.opensource.model.Utilisateur;
import com.example.api.opensource.service.UtilisateurService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("utilisateur")
public class UtilisateurController {
	
	@Autowired
	private UtilisateurService utilisateurService;
	
	@GetMapping
	@RolesAllowed("ADMIN_TOUCAN")
	public List<Utilisateur> findUtilisateur() {
		return utilisateurService.findAll();
	}
	
}
