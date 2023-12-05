package com.devteam.marktplaats.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devteam.marktplaats.model.Item;
import com.devteam.marktplaats.model.Order;
import com.devteam.marktplaats.model.Product;
import com.devteam.marktplaats.model.ShoppingCart;

public interface ItemRepository extends JpaRepository<Item, Long> {
	
	List<Item> findByOrder(Order order);

	List<Item> findByShoppingCart(ShoppingCart shoppingCart);

	void deleteByShoppingCart(ShoppingCart cart);
}
