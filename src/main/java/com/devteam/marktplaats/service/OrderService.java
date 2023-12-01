package com.devteam.marktplaats.service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devteam.marktplaats.model.Item;
import com.devteam.marktplaats.model.Order;
import com.devteam.marktplaats.model.User;
import com.devteam.marktplaats.persistence.OrderRepository;
import com.devteam.marktplaats.persistence.UserRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(long id) {
        return this.orderRepository.findById(id);
    }

    public Order createOrUpdate(Order order) {
        return this.orderRepository.save(order);
    }

    public void deleteById(long id) {
        this.orderRepository.deleteById(id);
    }

    public List<Order> findByUser(long id) {
		Optional<User> optionalUser = this.userRepository.findById(id);
		if (optionalUser.isPresent()) {
			return this.orderRepository.findByUser(optionalUser.get());
		} else {
			return Collections.emptyList();
		}
	}
}
