package com.iessanvicente.scondhandmarket.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iessanvicente.scondhandmarket.models.Product;
import com.iessanvicente.scondhandmarket.models.Purchase;
import com.iessanvicente.scondhandmarket.models.User;
import com.iessanvicente.scondhandmarket.repositories.ProductRepository;
import com.iessanvicente.scondhandmarket.upload.StorageService;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	StorageService storageService;
	
	public Product insert(Product p) {
		return productRepository.save(p);
	}
	
	public void delete(Product p) {
		productRepository.delete(p);
		if(p.getImage() != null) {
			storageService.delete(p.getImage());
		}
	}
	
	public void delete(long id) {
		productRepository.deleteById(id);
		String filename = findById(id).getImage();
		if(filename != null) {
			storageService.delete(filename);
		}
	}
	
	public Product update(Product p) {
		return productRepository.save(p);
	}
	
	public Product findById(long id) {
		return productRepository.findById(id).orElse(null);
	}
	
	public List<Product> findAll(){
		return productRepository.findAll();
	}
	
	public List<Product> findByOwner(User owner){
		return productRepository.findByOwner(owner);
	}
	
	public List<Product> findByPurchase(Purchase purchase){
		return productRepository.findByPurchase(purchase);
	}
	
	public List<Product> getNotSold(){
		return productRepository.findByPurchaseIsNull();
	}
	
	public List<Product> find(String query){
		return productRepository.findByNameContainsIgnoreCaseAndPurchaseIsNull(query);
	}
	
	public List<Product> findMyProducts(String query, User u){
		return productRepository.findByNameContainsIgnoreCaseAndOwner(query, u);
	}
	
	public List<Product> findById(List<Long> ids){
		return productRepository.findAllById(ids);
	}
}
