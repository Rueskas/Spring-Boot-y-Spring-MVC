package com.iessanvicente.scondhandmarket.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iessanvicente.scondhandmarket.models.Purchase;
import com.iessanvicente.scondhandmarket.models.User;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

	List<Purchase> findByOwner(User owner);
}
