package com.iessanvicente.scondhandmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iessanvicente.scondhandmarket.models.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findFirstByEmail(String email);
}
