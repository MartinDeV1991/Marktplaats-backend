package com.devteam.marktplaats.service;

import com.devteam.marktplaats.model.Item;
import com.devteam.marktplaats.model.Order;
import com.devteam.marktplaats.model.Product;
import com.devteam.marktplaats.model.ShoppingCart;
import com.devteam.marktplaats.persistence.ItemRepository;
import com.devteam.marktplaats.persistence.OrderRepository;
import com.devteam.marktplaats.persistence.ShoppingCartRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

	@Autowired
    private ItemRepository itemRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private ShoppingCartRepository shoppingCartRepository;

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    public Optional<Item> findById(long id) {
        return itemRepository.findById(id);
    }

    public Item createOrUpdate(Item item) {
        return itemRepository.save(item);
    }

    public void deleteById(long id) {
        itemRepository.deleteById(id);
    }

    public List<Item> findByOrder(long id) {
		Optional<Order> optionalOrder = this.orderRepository.findById(id);
		if (optionalOrder.isPresent()) {
			return this.itemRepository.findByOrder(optionalOrder.get());
		} else {
			return Collections.emptyList();
		}
	}
    
    public List<Item> findByShoppingCart(long id) {
		Optional<ShoppingCart> optionalShoppingCart = this.shoppingCartRepository.findById(id);
		if (optionalShoppingCart.isPresent()) {
			return this.itemRepository.findByShoppingCart(optionalShoppingCart.get());
		} else {
			return Collections.emptyList();
		}
	}
    
}
