package com.devteam.marktplaats.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devteam.marktplaats.model.Product;
import com.devteam.marktplaats.service.ProductService;


@RestController
@RequestMapping("api/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
//	@GetMapping
//	public String findAllProducts() {
//		String text = "Hello world!";
//	    return text;
//	}
	
	@GetMapping
	public List<Product> findAllProducts() {
		return productService.getAllProducts();
	}
	
	@PostMapping
	public Product create(@RequestBody Product product) {
		return this.productService.createOrUpdate(product);
	}
	
	@DeleteMapping("{id}")
	public void deleteById(@PathVariable long id) {
		this.productService.deleteById(id);
	}
	
}
