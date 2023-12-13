package com.devteam.marktplaats.api;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devteam.marktplaats.dto.ProductDTO;
import com.devteam.marktplaats.model.Product;
import com.devteam.marktplaats.service.ProductService;

@RestController
@RequestMapping("api/foto")
public class FotoController {

	@Autowired
	private ProductService productService;

	@GetMapping
	public List<ProductDTO> findAllProducts() {
		return productService.getAllProducts()
				.stream()
				.map(ProductDTO::new)
				.collect(Collectors.toList());
	}

	@GetMapping("{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable long id) {
		return productService.findById(id)
				.map(ProductDTO::new)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("by_user/{id}")
	public ResponseEntity<List<ProductDTO>> findByUser(@PathVariable long id) {
		List<ProductDTO> productDTOList = productService.findByUser(id)
				.stream().map(ProductDTO::new)
				.collect(Collectors.toList());
		return ResponseEntity.ok(productDTOList);
	}	
	
	@PostMapping("user/{user_id}")
	public ProductDTO create(@PathVariable long user_id, @RequestBody Product product) {
		System.out.println(product.getFoto());
		return new ProductDTO(this.productService.create(product, user_id));
	}

	@DeleteMapping("{id}")
	public void deleteById(@PathVariable long id) {
		this.productService.deleteById(id);
	}

	@PutMapping("{id}")
	public ResponseEntity<ProductDTO> updateById(@PathVariable long id, @RequestBody Product input) {
		Optional<Product> optionalTarget = this.productService.findById(id);

		if (optionalTarget.isEmpty()) {
			return null;
		}

		Product target = optionalTarget.get();
		target.setProductType(input.getProductType());
		target.setProductName(input.getProductName());
		target.setProductDescription(input.getProductDescription());
		target.setPrice(input.getPrice());
		target.setWeight(input.getWeight());
		target.setSize(input.getSize());
		target.setFoto(input.getFoto());

		ProductDTO updated = new ProductDTO(this.productService.createOrUpdate(target));
		return ResponseEntity.ok(updated);

	}
}
