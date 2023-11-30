package com.devteam.marktplaats.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devteam.marktplaats.model.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

}
