package com.devteam.marktplaats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devteam.marktplaats.model.Order;
import com.devteam.marktplaats.persistence.OrderRepository;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

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
}
