package com.devteam.marktplaats.service;

import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devteam.marktplaats.model.User;
import com.devteam.marktplaats.persistence.UserRepository;

@Service
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;


	public Optional<? extends User> authenticate(String email, String password) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			if (password.equals(user.getPassword())) {
				user.setToken(RandomStringUtils.random(100, true, true));
				userRepository.save(user);
				return optionalUser;
			}
		}

		return Optional.empty();
	}

}
