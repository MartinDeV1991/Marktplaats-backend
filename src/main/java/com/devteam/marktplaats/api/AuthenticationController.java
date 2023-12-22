package com.devteam.marktplaats.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.devteam.marktplaats.dto.LoginRequestDTO;
import com.devteam.marktplaats.dto.LoginResponseDTO;
import com.devteam.marktplaats.model.User;
import com.devteam.marktplaats.service.AuthenticationService;

@RestController
public class AuthenticationController {
	
	@Autowired
	private AuthenticationService authenticationService;

	@PostMapping("auth/login")
	public LoginResponseDTO login(@RequestBody LoginRequestDTO DTO) {
		Optional<? extends User> optional = authenticationService.authenticate(DTO.getEmail(), DTO.getPassword());
		
		if (optional.isPresent()) {
			User user = optional.get();
			return new LoginResponseDTO(true, user.getToken(), user.getName(), user.getId());
		}
		
		return new LoginResponseDTO(false, null, null, 0);
	}
	
}
