package com.devteam.marktplaats.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devteam.marktplaats.model.Order;
import com.devteam.marktplaats.model.User;

public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findByUser(User user);

}
