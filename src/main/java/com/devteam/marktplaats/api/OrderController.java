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

    @GetMapping("by_user/{user_id}")
    public ResponseEntity<List<OrderDTO>> findByUser(@PathVariable long user_id) {
    	List<OrderDTO> orderDTOList = orderService.findByUser(user_id)
    			.stream()
				.map(OrderDTO::new)
				.collect(Collectors.toList());
    	 return ResponseEntity.ok(orderDTOList);
    }
    
    
    @PostMapping("user/{user_id}")
    public Order create(@PathVariable long user_id, @RequestBody Order order) {
    	return this.orderService.create(order, user_id);
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

        Order updated = this.orderService.update(target);
        return ResponseEntity.ok(updated);
    }
}
