package com.devteam.marktplaats.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devteam.marktplaats.model.ShoppingCart;
import com.devteam.marktplaats.service.ShoppingCartService;

@RestController
@RequestMapping("api/shoppingcart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping
    public List<ShoppingCart> findAllShoppingCarts() {
        return shoppingCartService.getAllShoppingCarts();
    }

    @GetMapping("{id}")
    public ResponseEntity<ShoppingCart> findById(@PathVariable long id) {
        Optional<ShoppingCart> optionalShoppingCart = shoppingCartService.findById(id);
        if (optionalShoppingCart.isPresent()) {
            return ResponseEntity.ok(optionalShoppingCart.get());
        } else {
            return ResponseEntity.notFound().build();
        }
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
