package com.devteam.marktplaats.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devteam.marktplaats.model.Product;



public interface ProductRepository extends JpaRepository<Product, Long>{

}
