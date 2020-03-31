package com.example.api.opensource.service;

import java.util.ArrayList;
import java.util.List;

import com.example.api.opensource.model.Utilisateur;

import org.springframework.stereotype.Service;

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
