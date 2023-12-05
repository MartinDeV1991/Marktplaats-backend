package com.devteam.marktplaats.service;

import com.devteam.marktplaats.model.Item;
import com.devteam.marktplaats.model.Order;
import com.devteam.marktplaats.model.Product;
import com.devteam.marktplaats.model.ShoppingCart;
import com.devteam.marktplaats.model.User;
import com.devteam.marktplaats.persistence.ItemRepository;
import com.devteam.marktplaats.persistence.OrderRepository;
import com.devteam.marktplaats.persistence.ProductRepository;
import com.devteam.marktplaats.persistence.ShoppingCartRepository;
import com.devteam.marktplaats.persistence.UserRepository;

import jakarta.transaction.Transactional;

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
	private ProductRepository productRepository;

	@Autowired
	private ShoppingCartRepository shoppingCartRepository;
	
	@Autowired
	private UserRepository userRepository;

	public List<Item> getAllItems() {
		return itemRepository.findAll();
	}

	public Optional<Item> findById(long id) {
		return itemRepository.findById(id);
	}

	public Item addToCart(Item item, long product_id, long cart_id) {
		Optional<Product> optionalProduct = productRepository.findById(product_id);
		Optional<ShoppingCart> optionalShoppingCart = shoppingCartRepository.findById(cart_id);
		if (optionalProduct.isPresent() && optionalShoppingCart.isPresent()) {
			item.setProduct(optionalProduct.get());
			item.setShoppingCart(optionalShoppingCart.get());
			return itemRepository.save(item);
		}
		return null;
	}

	public Item create(Item item) {
		return itemRepository.save(item);
	}

	public Item update(Item item) {
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
	
	@Transactional
	public void emptyCart(long user_id) {
		Optional<User> optionalUser = this.userRepository.findById(user_id);
		if (optionalUser.isPresent()) {
    		User user = optionalUser.get();
    		ShoppingCart cart = user.getShoppingCart();
    		itemRepository.deleteByShoppingCart(cart);    		
		}
	}

}
