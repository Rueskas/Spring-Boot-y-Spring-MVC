package com.iessanvicente.spring.controladores;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
	
	/*
	@GetMapping("/")
	public String welcome() {
		return "index";
	}
	*/
	
	@GetMapping("/")
	public String welcome(Model model) {
		model.addAttribute("mensaje", "Mensaje enviado");
		return "index";
	}
	
	@GetMapping("/welcome")
	public String saludo(Model model) {
		model.addAttribute("saludo", "Saludo a quien se haya conectado");
		return "saludo";
	}
	
	/*
	@GetMapping("/saludos")
	public String saludo(@RequestParam("name") String name, Model model) {
		model.addAttribute("saludo", "Saludo: " + name);
		return "saludo";
	}
	*/
	
	/*
	@GetMapping("/saludos")
	public String saludo(@RequestParam(name="name", required=false, defaultValue="Mundo") String name, Model model) {
		model.addAttribute("saludo", "Saludo: " + name);
		return "saludo";
	}
	*/

	@GetMapping("/saludo")
	public String saludo(@RequestParam(name="name") Optional<String> name, Model model) {
		model.addAttribute("saludo", "Saludo: " + name.orElse("Mundo"));
		return "saludo";
	}
	

	@GetMapping("/saludo/{name}")
	public String saludo(@PathVariable String name, Model model) {
		model.addAttribute("saludo", "Saludo: " + name);
		return "saludo";
	}
	
	@GetMapping("/saludoapellido")
	public String saludo(@RequestParam(name="name") Optional<String> name,
			@RequestParam(name="apellidos") Optional<String> apellidos, Model model) {
		model.addAttribute("saludo", "Saludo: " + name.orElse("") + " " + apellidos.orElse(""));
		return "saludo";
	}

	@GetMapping("/saludoapellido/{name}/{apellidos}")
	public String saludoApellido(@PathVariable String name, @PathVariable String apellidos, Model model) {
		model.addAttribute("saludo", "Saludo: " + name + " " + apellidos);
		return "saludo";
	}
}
