package com.devteam.marktplaats.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devteam.marktplaats.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{

}
