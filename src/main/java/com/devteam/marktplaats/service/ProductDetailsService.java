package com.devteam.marktplaats.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devteam.marktplaats.model.Foto;
import com.devteam.marktplaats.model.ProductDetails;
import com.devteam.marktplaats.persistence.FotoRepository;
import com.devteam.marktplaats.persistence.ProductDetailsRepository;

@Service
public class ProductDetailsService {

	@Autowired
	private ProductDetailsRepository productDetailsRepository;
	
	
	public List<ProductDetails> getAllFotos() {
		return productDetailsRepository.findAll();
	}

	public Optional<ProductDetails> findById(long id) {
		return this.productDetailsRepository.findById(id); 
	}
	
	public ProductDetails createOrUpdate(ProductDetails productDetails) {
		return this.productDetailsRepository.save(productDetails);
	}

	public void deleteById(long id) {
		this.productDetailsRepository.deleteById(id);
		
	}
	
}
