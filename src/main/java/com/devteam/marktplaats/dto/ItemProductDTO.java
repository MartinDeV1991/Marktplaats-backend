package com.devteam.marktplaats.dto;

import java.util.ArrayList;
import java.util.List;

import com.devteam.marktplaats.model.Foto;
import com.devteam.marktplaats.model.Item;
import com.devteam.marktplaats.model.Product;
import com.devteam.marktplaats.model.ProductDetails;

public class ItemProductDTO {

    private long itemId;
    private int quantity;
    
    private long productId;
    private String productType;
    private String productName;
    private String productDescription;
    private double price;
    private double weight;
    private String size;
    private List<String> foto;
	private List<String> propertyName;
	private List<String> propertyValue;
    
    public ItemProductDTO(Item item, Product product) {
        this.itemId = item.getId();
        this.quantity = item.getQuantity();
        
        this.productId = product.getId();
        this.productType = product.getProductType();
        this.productName = product.getProductName();
        this.productDescription = product.getProductDescription();
        this.price = product.getPrice();
        this.weight = product.getWeight();
        this.size = product.getSize();
        this.foto = new ArrayList<>();

		if (product.getFoto() != null) {
			for (Foto foto : product.getFoto()) {
				this.foto.add(foto.getUrl());
			}
		}
		
		this.propertyName = new ArrayList<>();
		this.propertyValue = new ArrayList<>();

		if (product.getProductDetails() != null) {
			for (ProductDetails productDetails : product.getProductDetails()) {
				this.propertyName.add(productDetails.getPropertyName());
				this.propertyValue.add(productDetails.getPropertyValue());
			}
		}
    }

	public long getItemId() {
		return itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public long getProductId() {
		return productId;
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

	public List<String> getFoto() {
		return foto;
	}

	public List<String> getPropertyName() {
		return propertyName;
	}

	public List<String> getPropertyValue() {
		return propertyValue;
	}
	
}
