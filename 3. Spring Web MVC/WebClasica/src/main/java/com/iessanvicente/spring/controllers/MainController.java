package com.iessanvicente.spring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String root(Model model) {
		model.addAttribute("title", "Welcome to our WEB");
		return "root";
	}
	
	@GetMapping("/who")
	public String who(Model model) {
		model.addAttribute("title", "Welcome to our WEB");
		model.addAttribute("head", "Who we are?");
		model.addAttribute("content", "I dont know men xd");
		return "who";
	}
	
	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("title", "Welcome to our WEB");
		model.addAttribute("content", "If you want to contact us. CALL ME.");
		return "contact";
	}
	
}
