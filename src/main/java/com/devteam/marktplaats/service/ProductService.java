package com.devteam.marktplaats.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devteam.marktplaats.model.Product;
import com.devteam.marktplaats.persistence.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product createOrUpdate(Product product) {
		return this.productRepository.save(product);
	}

	public void deleteById(long id) {
		this.productRepository.deleteById(id);
		
	}
	
}
