package com.devteam.marktplaats.dto;

public class LoginResponseDTO {
	
	private boolean success;

	private String token;
	
	private String name;
	
	private long id;
	
	public LoginResponseDTO(boolean success, String token, String name, long id) {
		super();
		this.success = success;
		this.token = token;
		this.name = name;
		this.id = id;
	}
	
	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
