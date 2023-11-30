package com.devteam.marktplaats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devteam.marktplaats.model.ShoppingCart;
import com.devteam.marktplaats.persistence.ShoppingCartRepository;

@Service
public class ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

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
}
