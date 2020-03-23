package com.iessanvicente.scondhandmarket.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iessanvicente.scondhandmarket.models.Product;
import com.iessanvicente.scondhandmarket.models.Purchase;
import com.iessanvicente.scondhandmarket.models.User;

public interface ProductRepository extends JpaRepository<Product, Long> {

	List<Product> findByOwner(User owner);
	
	List<Product> findByPurchase(Purchase purchase);
	
	List<Product> findByPurchaseIsNull();
	
	List<Product> findByNameContainsIgnoreCaseAndPurchaseIsNull(String query);
	
	List<Product> findByNameContainsIgnoreCaseAndOwner(String name, User owner);
	
}
