package com.devteam.marktplaats.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devteam.marktplaats.model.User;
import com.devteam.marktplaats.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> findAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (optionalUser.isPresent()) {
            return ResponseEntity.ok(optionalUser.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return this.userService.createOrUpdate(user);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable long id) {
        this.userService.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateById(@PathVariable long id, @RequestBody User input) {
        Optional<User> optionalTarget = this.userService.findById(id);

        if (optionalTarget.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        User target = optionalTarget.get();
        target.setName(input.getName());
        target.setAddress(input.getAddress());
        target.setEmail(input.getEmail());
        target.setPassword(input.getPassword());
        target.setPaymentDetails(input.getPaymentDetails());
        

        User updated = this.userService.createOrUpdate(target);
        return ResponseEntity.ok(updated);
    }
}
