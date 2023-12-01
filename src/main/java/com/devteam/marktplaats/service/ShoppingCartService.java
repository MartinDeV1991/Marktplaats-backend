package com.devteam.marktplaats.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.devteam.marktplaats.model.Order;
import com.devteam.marktplaats.model.ShoppingCart;
import com.devteam.marktplaats.model.User;
import com.devteam.marktplaats.persistence.ShoppingCartRepository;
import com.devteam.marktplaats.persistence.UserRepository;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private UserRepository userRepository;
    
    public List<ShoppingCart> getAllShoppingCarts() {
        return shoppingCartRepository.findAll();
    }

    public Optional<ShoppingCart> findById(long id) {
        return this.shoppingCartRepository.findById(id);
    }

    public ShoppingCart createOrUpdate(ShoppingCart shoppingCart) {
        return this.shoppingCartRepository.save(shoppingCart);
    }

    public void deleteById(long id) {
        this.shoppingCartRepository.deleteById(id);
    }
    
    public Optional<ShoppingCart> findByUser(long id) {
		Optional<User> optionalUser = this.userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return Optional.ofNullable(this.shoppingCartRepository.findByUser(optionalUser.get()));
		} else {
			return Optional.empty();
		}
	}
}
