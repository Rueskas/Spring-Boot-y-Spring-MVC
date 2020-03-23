package com.iessanvicente.scondhandmarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessanvicente.scondhandmarket.models.Product;
import com.iessanvicente.scondhandmarket.models.Purchase;
import com.iessanvicente.scondhandmarket.models.User;
import com.iessanvicente.scondhandmarket.repositories.ProductRepository;
import com.iessanvicente.scondhandmarket.repositories.PurchaseRepository;

@Service
public class PurchaseService {

	@Autowired
	PurchaseRepository purchaseRepository;
	
	@Autowired
	ProductRepository productRepository;

	public Purchase insert(Purchase p, User u) {
		p.setOwner(u);
		return purchaseRepository.save(p);
	}
	
	public Purchase insert(Purchase p) {
		return purchaseRepository.save(p);
	}
	
	public void addProductToPurchase(Purchase p, Product c) {
		c.setPurchase(p);
	}
	
	public List<Purchase> findAll(){
		return purchaseRepository.findAll();
	}
	
	public Purchase findById(long id){
		return purchaseRepository.findById(id).orElse(null);
	}
	
	public List<Purchase> findByOwner(User u){
		return purchaseRepository.findByOwner(u);
	}
}
