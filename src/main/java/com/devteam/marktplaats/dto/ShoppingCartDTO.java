package com.devteam.marktplaats.dto;

import com.devteam.marktplaats.model.ShoppingCart;

import java.time.LocalDate;

public class ShoppingCartDTO {

    private LocalDate expectedDeliveryDate;

    public ShoppingCartDTO(ShoppingCart shoppingCart) {
        this.expectedDeliveryDate = shoppingCart.getExpectedDeliveryDate();
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }
}
