package com.devteam.marktplaats.api;

import com.devteam.marktplaats.dto.ItemDTO;
import com.devteam.marktplaats.dto.ItemProductDTO;
import com.devteam.marktplaats.dto.ProductDTO;
import com.devteam.marktplaats.model.Item;
import com.devteam.marktplaats.model.Product;
import com.devteam.marktplaats.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/item")
public class ItemController {

	@Autowired
	private ItemService itemService;

	@GetMapping
    public List<ItemProductDTO> getAllItems() {
        return itemService.getAllItems()
        		.stream()
        		.map(item -> new ItemProductDTO(item, item.getProduct()))
        		.collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemProductDTO> getById(@PathVariable long id) {
        return itemService.findById(id)
                .map(item -> new ItemProductDTO(item, item.getProduct()))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("by_order/{id}")
	public ResponseEntity<List<ItemProductDTO>> findByOrder(@PathVariable long id) {
		List<ItemProductDTO> itemProductDTOList = itemService.findByOrder(id)
				.stream()
				.map(item -> new ItemProductDTO(item, item.getProduct()))
				.collect(Collectors.toList());
		return ResponseEntity.ok(itemProductDTOList);
	}
    
    @GetMapping("by_shopping_cart/{id}")
	public ResponseEntity<List<ItemProductDTO>> findByShoppingCart(@PathVariable long id) {
		List<ItemProductDTO> itemProductDTOList = itemService.findByShoppingCart(id)
				.stream()
				.map(item -> new ItemProductDTO(item, item.getProduct()))
				.collect(Collectors.toList());
		return ResponseEntity.ok(itemProductDTOList);
	}
    
	@PostMapping("add_to_cart/{product_id}/{shopping_cart_id}")
	public Item addToCart(@PathVariable long product_id, @PathVariable long shopping_cart_id, @RequestBody Item item) {
		return itemService.addToCart(item, product_id, shopping_cart_id);
	}

	@DeleteMapping("empty_cart/{user_id}")
	public void deleteByShoppingCart(@PathVariable long user_id) {
		itemService.emptyCart(user_id);
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable long id) {
		itemService.deleteById(id);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Item> updateItem(@PathVariable long id, @RequestBody Item input) {
		Optional<Item> optionalTarget = this.itemService.findById(id);
		
		if (optionalTarget.isEmpty()) {
			return null;
		}
		Item target = optionalTarget.get();
		target.setQuantity(input.getQuantity());
		
		Item updated = this.itemService.update(target);
		return ResponseEntity.ok(updated);
	}

}
