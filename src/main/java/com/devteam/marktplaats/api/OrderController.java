package com.devteam.marktplaats.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devteam.marktplaats.dto.OrderDTO;
import com.devteam.marktplaats.dto.ProductDTO;
import com.devteam.marktplaats.model.Order;
import com.devteam.marktplaats.service.OrderService;

@RestController
@RequestMapping("api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<OrderDTO> findAllOrders() {
        return orderService.getAllOrders()
        		.stream()
				.map(OrderDTO::new)
				.collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDTO> findById(@PathVariable long id) {
    	return orderService.findById(id)
				.map(OrderDTO::new)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
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
