package com.devteam.marktplaats.dto;

import com.devteam.marktplaats.model.User;

public class UserDTO {

	private long id;
	private String name;
    private String address;
    private String email;
    private String password;
    private String paymentDetails;

    public UserDTO(User user) {
    	this.id = user.getId();
    	this.name = user.getName();
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.paymentDetails = user.getPaymentDetails();
    }

    public long getId() {
		return id;
	}
    
    public String getName() {
    	return name;
    }
    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }
}
