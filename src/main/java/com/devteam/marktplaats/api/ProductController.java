package com.devteam.marktplaats.api;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devteam.marktplaats.dto.ProductDTO;
import com.devteam.marktplaats.model.Foto;
import com.devteam.marktplaats.model.Product;
import com.devteam.marktplaats.model.ProductDetails;
import com.devteam.marktplaats.persistence.FotoRepository;
import com.devteam.marktplaats.persistence.ProductDetailsRepository;
import com.devteam.marktplaats.service.CloudinaryService;
import com.devteam.marktplaats.service.ProductService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("api/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private FotoRepository fotoRepository;

	@Autowired
	private ProductDetailsRepository productDetailsRepository;

	@Autowired
	private CloudinaryService cloudinaryService;

	@GetMapping
	public List<ProductDTO> findAllProducts() {
		return productService.getAllProducts().stream().map(ProductDTO::new).collect(Collectors.toList());
	}

	@GetMapping("/search_name/{name}")
	public List<ProductDTO> findProductsByName(@PathVariable String name) {
		return productService.findProductsByName(name).stream().map(ProductDTO::new).collect(Collectors.toList());
	}

	@GetMapping("{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable long id) {
		return productService.findById(id).map(ProductDTO::new).map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("by_user/{id}")
	public ResponseEntity<List<ProductDTO>> findByUser(@PathVariable long id) {
		List<ProductDTO> productDTOList = productService.findByUser(id).stream().map(ProductDTO::new)
				.collect(Collectors.toList());
		return ResponseEntity.ok(productDTOList);
	}

	@PostMapping("user/{user_id}")
	public ProductDTO create(@PathVariable long user_id, @RequestBody Product product) {
		System.out.println("UserId: " + user_id);
		System.out.println("productDetails:  " + product.getProductDetails());
		return new ProductDTO(this.productService.create(product, user_id));
	}

	@DeleteMapping("{id}")
	public void deleteById(@PathVariable long id) {
		this.productService.deleteById(id);
	}

	@Transactional
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

		List<Foto> fotos = target.getFoto();
		if (target.getFoto() != null) {
			for (Foto foto : fotos) {
				this.fotoRepository.deleteById(foto.getId());

			}
		}

		List<ProductDetails> productDetails = target.getProductDetails();
		if (target.getProductDetails() != null) {
			for (ProductDetails productDetail : productDetails) {
				this.productDetailsRepository.deleteById(productDetail.getId());

			}
		}

		target.setFoto(input.getFoto());
		target.setProductDetails(input.getProductDetails());

		ProductDTO updated = new ProductDTO(this.productService.update(target));
		return ResponseEntity.ok(updated);

	}

	@PostMapping("/upload_file/{id}")
	public String uploadImage(@RequestParam("file") MultipartFile file, @PathVariable long id) {
		String imageUrl = cloudinaryService.uploadFile(file);
		
		Optional<Product> optionalTarget = this.productService.findById(id);
		if (optionalTarget.isEmpty()) {
			return null;
		}
		Product target = optionalTarget.get();
		Foto foto = new Foto();
        foto.setUrl(imageUrl);
        foto.setProduct(target);
        fotoRepository.save(foto);
        
		return imageUrl;
	}

	@PostMapping("/upload_files/{id}")
	public List<String> uploadImage(@RequestParam("file") List<MultipartFile> files, @PathVariable long id) {
		List<String> imageUrls = new ArrayList<>();
		for (MultipartFile file : files) {
			String imageUrl = cloudinaryService.uploadFile(file);
			imageUrls.add(imageUrl);
		}
		
		Optional<Product> optionalTarget = this.productService.findById(id);
		if (optionalTarget.isEmpty()) {
			return null;
		}
		Product target = optionalTarget.get();
		List<Foto> fotos = new ArrayList<>();
		
		for (String imageUrl : imageUrls) {
	        Foto foto = new Foto();
	        foto.setUrl(imageUrl);
	        foto.setProduct(target);
	        fotoRepository.save(foto);
	    }
		
		return imageUrls;
	}

}
