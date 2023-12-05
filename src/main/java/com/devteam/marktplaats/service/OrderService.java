package com.devteam.marktplaats.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devteam.marktplaats.model.Item;
import com.devteam.marktplaats.model.Order;
import com.devteam.marktplaats.model.ShoppingCart;
import com.devteam.marktplaats.model.User;
import com.devteam.marktplaats.persistence.ItemRepository;
import com.devteam.marktplaats.persistence.OrderRepository;
import com.devteam.marktplaats.persistence.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;
    
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(long id) {
        return this.orderRepository.findById(id);
    }

    public Order update(Order order) {
        return this.orderRepository.save(order);
    }
    
    @Transactional
    public Order create(Order order, long user_id) {
    	Optional<User> optionalUser = this.userRepository.findById(user_id);
    	
    	if (optionalUser.isPresent()) {
    		User user = optionalUser.get();
    		ShoppingCart cart = user.getShoppingCart();

    		order.setUser(user);
    		Order savedOrder = this.orderRepository.save(order);
    		List<Item> itemsFromCart = cart.getItem();
            for (Item item : itemsFromCart) {
                Item newItem = new Item();
                newItem.setProduct(item.getProduct());
                newItem.setQuantity(item.getQuantity());
                newItem.setOrder(order);
                itemRepository.save(newItem);
            }
            return savedOrder;
    	}
        return null;
    }

    public void deleteById(long id) {
        this.orderRepository.deleteById(id);
    }

    public List<Order> findByUser(long user_id) {
		Optional<User> optionalUser = this.userRepository.findById(user_id);
		if (optionalUser.isPresent()) {
			return this.orderRepository.findByUser(optionalUser.get());
		} else {
			return Collections.emptyList();
		}
	}
}
