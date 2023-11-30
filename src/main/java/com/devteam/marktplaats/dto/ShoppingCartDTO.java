package com.devteam.marktplaats.dto;

import com.devteam.marktplaats.model.ShoppingCart;

import java.time.LocalDate;

public class ShoppingCartDTO {

	private long id;
    private LocalDate expectedDeliveryDate;

    public ShoppingCartDTO(ShoppingCart shoppingCart) {
    	this.id = shoppingCart.getId();
        this.expectedDeliveryDate = shoppingCart.getExpectedDeliveryDate();
    }

    public long getId() {
		return id;
	}
    
    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }
}
