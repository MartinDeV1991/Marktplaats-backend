package com.devteam.marktplaats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devteam.marktplaats.model.ShoppingCart;
import com.devteam.marktplaats.model.User;
import com.devteam.marktplaats.persistence.ShoppingCartRepository;
import com.devteam.marktplaats.persistence.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> findById(long id) {
		return this.userRepository.findById(id);
	}

	public User update(User user) {
		return this.userRepository.save(user);
	}

	public User create(User user) {
		this.userRepository.save(user);
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setUser(user);
		this.shoppingCartRepository.save(shoppingCart);
		return user;
	}

	public void deleteById(long id) {
		this.userRepository.deleteById(id);
	}
}
