package com.iessanvicente.scondhandmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.iessanvicente.scondhandmarket.models.User;
import com.iessanvicente.scondhandmarket.services.UserService;
import com.iessanvicente.scondhandmarket.upload.StorageService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	@Autowired
	StorageService storageService;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/public/";
	}
	
	@GetMapping("/auth/login")
	public String login(Model model){
		model.addAttribute("usuario", new User());
		return "login";
	}
	
	@PostMapping("/auth/register")
	public String register(@ModelAttribute User usuario, @RequestParam("file") MultipartFile file){
		if(!file.isEmpty()) {
			String image = storageService.store(file);
			usuario.setAvatar(MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "serveFile", image).build().toUriString());
		}
		userService.register(usuario);
		return "redirect:/auth/login";
	}
}
