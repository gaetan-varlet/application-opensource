package com.example.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.api.model.Utilisateur;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
	
	private static List<Utilisateur> utilisateurs = new ArrayList<>();
	
   static {
   	utilisateurs.add(new Utilisateur("azerty", "Toto"));
   	utilisateurs.add(new Utilisateur("qwerty", "Tutu"));
   }
	
	@Override
	public List<Utilisateur> findAll() {
		return utilisateurs;
	}
	
}
