package fr.example.api.insee.service;

import java.util.ArrayList;
import java.util.List;

import com.example.api.opensource.model.Utilisateur;
import com.example.api.opensource.service.UtilisateurService;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

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
