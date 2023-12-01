package com.devteam.marktplaats.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devteam.marktplaats.model.ShoppingCart;
import com.devteam.marktplaats.model.User;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

	ShoppingCart findByUser(User user);

}
