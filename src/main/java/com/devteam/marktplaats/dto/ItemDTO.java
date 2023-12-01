package com.devteam.marktplaats.dto;

import com.devteam.marktplaats.model.Item;

public class ItemDTO {

    private long id;
    private int quantity;


    public ItemDTO(Item item) {
        this.id = item.getId();
        this.quantity = item.getQuantity();
    }

	public long getId() {
		return id;
	}

	public int getQuantity() {
		return quantity;
	}

}
