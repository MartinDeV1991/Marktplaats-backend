package com.devteam.marktplaats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devteam.marktplaats.model.User;
import com.devteam.marktplaats.persistence.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> findById(long id) {
        return this.userRepository.findById(id);
    }

    public User createOrUpdate(User user) {
        return this.userRepository.save(user);
    }

    public void deleteById(long id) {
        this.userRepository.deleteById(id);
    }
}
