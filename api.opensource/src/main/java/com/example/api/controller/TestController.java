package com.example.api.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private Principal principal;

	@GetMapping("principal")
	public String getPrincipal() {
		return "Mon idep est " + principal.getName() + ".";
	}

	@GetMapping("hello")
	public String hello(HttpServletRequest request) {
		boolean bool1 = request.isUserInRole("ADMIN_TOUCAN");
		boolean bool2 = request.isUserInRole("Utilisateurs_DEV_Pelican2");
		return "Hello depuis le projet open source ! (" + bool1 + " " + bool2 + ")";
	}
}
