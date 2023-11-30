package com.devteam.marktplaats.dto;

import com.devteam.marktplaats.model.Order;

import java.time.LocalDate;

public class OrderDTO {

	private long id;
    private double totalPayment;
    private String deliveryAddress;
    private LocalDate deliveryDate;
    private LocalDate paymentDate;
    private String paymentMethod;
    private String status;

    public OrderDTO(Order order) {
    	this.id = order.getId();
        this.totalPayment = order.getTotalPayment();
        this.deliveryAddress = order.getDeliveryAddress();
        this.deliveryDate = order.getDeliveryDate();
        this.paymentDate = order.getPaymentDate();
        this.paymentMethod = order.getPaymentMethod();
        this.status = order.getStatus();
    }

    
    public long getId() {
		return id;
	}

	public double getTotalPayment() {
        return totalPayment;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getStatus() {
        return status;
    }
}
