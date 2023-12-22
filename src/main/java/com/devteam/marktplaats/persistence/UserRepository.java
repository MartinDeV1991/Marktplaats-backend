package com.devteam.marktplaats.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devteam.marktplaats.model.Product;
import com.devteam.marktplaats.model.User;



public interface UserRepository extends JpaRepository<User, Long>{

	Optional<User> findByEmail(String email);

}
