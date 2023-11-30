package com.devteam.marktplaats.service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devteam.marktplaats.dto.ProductDTO;
import com.devteam.marktplaats.model.Order;
import com.devteam.marktplaats.model.Product;
import com.devteam.marktplaats.model.User;
import com.devteam.marktplaats.persistence.OrderRepository;
import com.devteam.marktplaats.persistence.ProductRepository;
import com.devteam.marktplaats.persistence.UserRepository;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Optional<Product> findById(long id) {
		return this.productRepository.findById(id); 
	}
	
	public Product createOrUpdate(Product product) {
		return this.productRepository.save(product);
	}

	public void deleteById(long id) {
		this.productRepository.deleteById(id);
		
	}

	public List<Product> findByUser(long id) {
		Optional<User> optionalUser = this.userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return this.productRepository.findByUser(optionalUser.get());
		} else {
			return Collections.emptyList();
		}
		
	}

	public List<Product> findByOrder(long id) {
		Optional<Order> optionalOrder = this.orderRepository.findById(id);
		if (optionalOrder.isPresent()) {
			return this.productRepository.findByOrder(optionalOrder.get());
		} else {
			return Collections.emptyList();
		}
		
	}
	
}
