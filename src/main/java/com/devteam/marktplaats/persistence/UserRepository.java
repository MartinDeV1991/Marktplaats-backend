package com.devteam.marktplaats.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devteam.marktplaats.model.Product;
import com.devteam.marktplaats.model.User;



public interface UserRepository extends JpaRepository<User, Long>{

}
