package com.devteam.marktplaats.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devteam.marktplaats.model.Order;
import com.devteam.marktplaats.service.OrderService;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> findAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("{id}")
    public ResponseEntity<Order> findById(@PathVariable long id) {
        Optional<Order> optionalOrder = orderService.findById(id);
        if (optionalOrder.isPresent()) {
            return ResponseEntity.ok(optionalOrder.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return this.orderService.createOrUpdate(order);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable long id) {
        this.orderService.deleteById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<Order> updateById(@PathVariable long id, @RequestBody Order input) {
        Optional<Order> optionalTarget = this.orderService.findById(id);

        if (optionalTarget.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Order target = optionalTarget.get();
        target.setTotalPayment(input.getTotalPayment());
        target.setDeliveryAddress(input.getDeliveryAddress());
        target.setDeliveryDate(input.getDeliveryDate());
        target.setPaymentDate(input.getPaymentDate());
        target.setPaymentMethod(input.getPaymentMethod());
        target.setStatus(input.getStatus());

        Order updated = this.orderService.createOrUpdate(target);
        return ResponseEntity.ok(updated);
    }
}
