package com.devteam.marktplaats.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devteam.marktplaats.dto.ProductDTO;
import com.devteam.marktplaats.dto.UserDTO;
import com.devteam.marktplaats.model.User;
import com.devteam.marktplaats.service.UserService;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> findAllUsers() {
        return userService.getAllUsers()
        		.stream()
				.map(UserDTO::new)
				.collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable long id) {
    	return userService.findById(id)
				.map(UserDTO::new)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UserDTO create(@RequestBody User user) {
        return new UserDTO(this.userService.create(user));
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable long id) {
        this.userService.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserDTO> updateById(@PathVariable long id, @RequestBody User input) {
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

        UserDTO updated = new UserDTO(this.userService.update(target));
        return ResponseEntity.ok(updated);
    }
}
