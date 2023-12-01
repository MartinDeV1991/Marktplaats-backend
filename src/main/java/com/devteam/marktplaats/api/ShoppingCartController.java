package com.devteam.marktplaats.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devteam.marktplaats.dto.ProductDTO;
import com.devteam.marktplaats.dto.ShoppingCartDTO;
import com.devteam.marktplaats.model.ShoppingCart;
import com.devteam.marktplaats.service.ShoppingCartService;

@RestController
@RequestMapping("api/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping
    public List<ShoppingCartDTO> findAllShoppingCarts() {
        return shoppingCartService.getAllShoppingCarts()
        		.stream()
				.map(ShoppingCartDTO::new)
				.collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ShoppingCartDTO> findById(@PathVariable long id) {
    	return shoppingCartService.findById(id)
				.map(ShoppingCartDTO::new)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("by_user/{id}")
    public ResponseEntity<ShoppingCartDTO> findByUser(@PathVariable long id) {
    	return shoppingCartService.findByUser(id)
                .map(ShoppingCartDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    public ShoppingCart create(@RequestBody ShoppingCart shoppingCart) {
        return this.shoppingCartService.createOrUpdate(shoppingCart);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable long id) {
        this.shoppingCartService.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<ShoppingCart> updateById(@PathVariable long id, @RequestBody ShoppingCart input) {
        Optional<ShoppingCart> optionalTarget = this.shoppingCartService.findById(id);

        if (optionalTarget.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        ShoppingCart target = optionalTarget.get();
        target.setExpectedDeliveryDate(input.getExpectedDeliveryDate());

        ShoppingCart updated = this.shoppingCartService.createOrUpdate(target);
        return ResponseEntity.ok(updated);
    }
}
