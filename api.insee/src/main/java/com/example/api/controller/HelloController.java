package com.example.api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@GetMapping("hello2")
	public String hello() {
		return "Hello depuis le projet INSEE !";
	}
}
