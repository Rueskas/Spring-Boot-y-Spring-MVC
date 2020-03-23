package com.iessanvicente.scondhandmarket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iessanvicente.scondhandmarket.models.Product;
import com.iessanvicente.scondhandmarket.services.ProductService;

@Controller
@RequestMapping("/public")
public class PublicController {

	@Autowired
	ProductService productService;
	
	@ModelAttribute("productos")
	public List<Product> productsNotSold(){
		return productService.getNotSold();
	}
	
	@GetMapping({"/", "/index"})
	public String index(Model model, @RequestParam(name="q", required=false) String query) {
		if(query != null) {
			model.addAttribute("productos", productService.find(query));
		}
		return "index";
	}
	
	@GetMapping("producto/{id}")
	public String showProduct(Model model, @PathVariable long id) {
		Product p = productService.findById(id);
		if(p != null) {
			model.addAttribute(p);
		}
		return "redirect:/public";
	}
}
