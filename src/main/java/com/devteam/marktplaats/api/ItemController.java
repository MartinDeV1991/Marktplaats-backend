package com.devteam.marktplaats.api;

import com.devteam.marktplaats.dto.ItemDTO;
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
    public List<ItemDTO> getAllItems() {
        return itemService.getAllItems()
        		.stream()
        		.map(ItemDTO::new)
        		.collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<ItemDTO> getById(@PathVariable long id) {
        return itemService.findById(id)
                .map(ItemDTO::new)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("by_order/{id}")
	public ResponseEntity<List<ItemDTO>> findByOrder(@PathVariable long id) {
		List<ItemDTO> itemDTOList = itemService.findByOrder(id)
				.stream().map(ItemDTO::new)
				.collect(Collectors.toList());
		return ResponseEntity.ok(itemDTOList);
	}
    
	@PostMapping
	public Item create(@RequestBody Item item) {
		return itemService.createOrUpdate(item);
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
		
		Item updated = this.itemService.createOrUpdate(target);
		return ResponseEntity.ok(updated);
	}

}
