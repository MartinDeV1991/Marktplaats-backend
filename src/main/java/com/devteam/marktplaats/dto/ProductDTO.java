package com.devteam.marktplaats.dto;

import java.util.List;

import com.devteam.marktplaats.model.Foto;
import com.devteam.marktplaats.model.Product;

public class ProductDTO {

	private long id;
    private String productType;
    private String productName;
    private String productDescription;
    private double price;
    private double weight;
    private String size;
    private List<Foto> foto;

    public ProductDTO(Product product) {
    	this.id = product.getId();
        this.productType = product.getProductType();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.price = product.getPrice();
        this.weight = product.getWeight();
        this.size = product.getSize();
        this.foto = product.getFoto();
    }

    public long getId() {
		return id;
	}
    
	public String getProductType() {
		return productType;
	}

	public String getProductName() {
		return productName;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public double getPrice() {
		return price;
	}

	public double getWeight() {
		return weight;
	}

	public String getSize() {
		return size;
	}

	public List<Foto> getFoto() {
		return foto;
	}

	
	
	
}
