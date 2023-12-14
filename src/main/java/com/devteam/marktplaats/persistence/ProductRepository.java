package com.devteam.marktplaats.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devteam.marktplaats.model.Order;
import com.devteam.marktplaats.model.Product;
import com.devteam.marktplaats.model.User;



public interface ProductRepository extends JpaRepository<Product, Long>{

	List<Product> findByUser(User user);

	List<Product> findByProductNameContaining(String name);
}
