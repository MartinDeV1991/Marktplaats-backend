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
    
    public Order create(Order order, long user_id) {
    	Optional<User> optionalUser = this.userRepository.findById(user_id);
    	
    	if (optionalUser.isPresent()) {
    		User user = optionalUser.get();
    		ShoppingCart cart = user.getShoppingCart();

    		order.setUser(user);
    		Order savedOrder = this.orderRepository.save(order);
    		List<Item> itemsFromCart = cart.getItem();
    		List<Long> itemIdsToDelete = new ArrayList<>();
            for (Item item : itemsFromCart) {
                Item newItem = new Item();
                newItem.setProduct(item.getProduct());
                newItem.setQuantity(item.getQuantity());
                newItem.setOrder(order);
                System.out.println("Before saving item");
                itemRepository.save(newItem);
                System.out.println("After saving item");
                itemIdsToDelete.add(item.getId());
            }
            System.out.println("Id list to delete: " + itemIdsToDelete);
            itemRepository.deleteById(itemIdsToDelete.get(0));
            itemIdsToDelete.forEach(itemId -> itemRepository.deleteById(itemId));
            System.out.println("Id list to delete: " + itemIdsToDelete);
            return null;
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
