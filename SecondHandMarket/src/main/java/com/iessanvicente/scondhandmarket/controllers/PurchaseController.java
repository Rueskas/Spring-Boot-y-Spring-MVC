package com.iessanvicente.scondhandmarket.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.iessanvicente.scondhandmarket.models.Product;
import com.iessanvicente.scondhandmarket.models.Purchase;
import com.iessanvicente.scondhandmarket.models.User;
import com.iessanvicente.scondhandmarket.services.ProductService;
import com.iessanvicente.scondhandmarket.services.PurchaseService;
import com.iessanvicente.scondhandmarket.services.UserService;

@Controller
@RequestMapping("/app")
public class PurchaseController {

	@Autowired
	PurchaseService purchaseService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	HttpSession session;
	
	private User user;
	
	@ModelAttribute("carrito")
	public List<Product>  shoppingCart(){
		@SuppressWarnings("unchecked")
		List<Long> content = (List<Long>) session.getAttribute("carrito");
		return (content == null)? null : productService.findById(content); 
	}
	
	@ModelAttribute("total_carrito")
	public Double totalPrice() {
		List<Product> products = shoppingCart();
		if(products != null) {
			return products.stream().mapToDouble(p -> p.getPrice()).sum();
		}
		return 0.0;
	}
	
	@ModelAttribute("mis_compras")
	public List<Purchase> shopping(){
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		user = userService.findByEmail(email);
		return purchaseService.findByOwner(user);
	}
	
	@GetMapping("/carrito")
	public String getShoppingCart(Model model) {
		return "app/compra/carrito";
	}
	
	@GetMapping("/carrito/add/{id}")
	public String addShoppingCart(Model model, @PathVariable Long id) {
		List<Long> content =  (List<Long>) session.getAttribute("carrito");
		if(content != null) {
			content = new ArrayList<>();
		}
		if(!content.contains(id)) {
			content.add(id);
		}
		
		session.setAttribute("content", content);
		return "redirect:/app/carrito";
	}
	
	@GetMapping("/carrito/eliminar/{id}")
	public String deleteFromShoppingCart(Model model, @PathVariable Long id) {
		List<Long> content =  (List<Long>) session.getAttribute("carrito");
		if(content == null) {
			return "redirect:/public";
		}
		content.remove(id);
		if(content.isEmpty()) {
			session.removeAttribute("carrito");
		} else {
			session.setAttribute("carrito", content);
		}
		return "redirect:/app/carrito";
	}
	
	@GetMapping("/carrito/finalizar")
	public String checkOut() {
		List<Long> content =  (List<Long>) session.getAttribute("carrito");
		if(content == null) {
			return "redirect:/public";
		}
		
		List<Product> products = shoppingCart();
		
		Purchase p = purchaseService.insert(new Purchase(), user);
		
		products.forEach(c -> purchaseService.addProductToPurchase(p, c));
		session.removeAttribute("carrito");
		
		return "redirect:/app/compra/factura/"+p.getId();
	}
	
	@GetMapping("/compra/factura/{id}")
	public String factura(Model model, @PathVariable Long id) {
		Purchase p = purchaseService.findById(id);
		List<Product> products = productService.findByPurchase(p);
		model.addAttribute("productos", products);
		model.addAttribute("compra", p);
		model.addAttribute("total_compra", products.stream().mapToDouble(c -> c.getPrice()).sum());
		return "/app/compra/factura";
	}
	
	@GetMapping("/miscompras")
	public String misCompras(){
		return "/app/compra/listado";
	}
}
