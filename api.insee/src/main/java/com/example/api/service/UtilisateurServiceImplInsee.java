package com.example.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.api.model.Utilisateur;
import com.example.api.service.UtilisateurService;

@Service
@Primary
public class UtilisateurServiceImplInsee implements UtilisateurService {

private static List<Utilisateur> utilisateurs = new ArrayList<>();
	
   static {
   	utilisateurs.add(new Utilisateur("njykl1", "Varlet"));
   	utilisateurs.add(new Utilisateur("f2wbnp", "Levitt"));
   }
	
	@Override
	public List<Utilisateur> findAll() {
		return utilisateurs;
	}
	
}
