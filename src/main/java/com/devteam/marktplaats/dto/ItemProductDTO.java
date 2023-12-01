package com.devteam.marktplaats.dto;

import com.devteam.marktplaats.model.Item;
import com.devteam.marktplaats.model.Product;

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
    private String foto;

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
        this.foto = product.getFoto();      
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

	public String getFoto() {
		return foto;
	}

}
