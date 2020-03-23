package com.iessanvicente.scondhandmarket.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import com.iessanvicente.scondhandmarket.models.Product;
import com.iessanvicente.scondhandmarket.models.User;
import com.iessanvicente.scondhandmarket.services.ProductService;
import com.iessanvicente.scondhandmarket.services.UserService;
import com.iessanvicente.scondhandmarket.upload.StorageService;

@Controller
@RequestMapping("/app")
public class ProductsController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	StorageService storageService;
	
	private User user;
	
	@ModelAttribute("misproductos")
	public List<Product> myProducts(){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.findByEmail(email);
		return productService.findByOwner(user);
	}
	
	@GetMapping("/misproductos")
	public String list(Model model, @RequestParam(name="q", required=false) String query) {
		if(query != null) {
			model.addAttribute("misproductos", productService.findMyProducts(query, user));
		}
		return "app/producto/lista";
	}
	
	@GetMapping("/misproductos/{id}/eliminar")
	public String eliminar(@PathVariable Long id) {
		Product p = productService.findById(id);
		if(p != null) {
			productService.delete(id);
		}
		return "redirect:/app/misproductos";
	}
	
	@GetMapping("producto/nuevo")
	public String newProductForm(Model model) {
		model.addAttribute("producto", new Product());
		return "app/producto/form";
	}
	
	@PostMapping("producto/nuevo/submit")
	public String newProductSubmit(@ModelAttribute Product producto, @RequestParam("file") MultipartFile file) {
		if(!file.isEmpty()) {
			String image = storageService.store(file);
			producto.setImage(MvcUriComponentsBuilder
					.fromMethodName(FilesController.class, "serveFile", image).build().toUriString());
		}
		producto.setOwner(user);
		productService.insert(producto);
		return "redirect:/app/misproductos";
	}
}
