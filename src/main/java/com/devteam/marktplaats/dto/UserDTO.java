package com.devteam.marktplaats.dto;

import com.devteam.marktplaats.model.User;

public class UserDTO {

    private String address;
    private String email;
    private String password;
    private String paymentDetails;

    public UserDTO(User user) {
        this.address = user.getAddress();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.paymentDetails = user.getPaymentDetails();
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
