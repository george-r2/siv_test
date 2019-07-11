package com.sivale.test.jama_test.social.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.sivale.test.jama_test.repository.UserDetailRepository;
import com.sivale.test.jama_test.repository.model.User;
import com.sivale.test.jama_test.social.UserDetail;

public class UserDetailsServiceImpl implements UserDetailsService {

	private UserDetailRepository userRepo;
	
	@Autowired
	public UserDetailsServiceImpl(UserDetailRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("No se encontro el usuario: " + username);
		}
		UserDetail principal = UserDetail.getBuilder()
			.firstName(user.getFirstName())
			.id(user.getId())
			.lastName(user.getLastName())
			.password(user.getPassword())
			.role(user.getRole())
			.socialSignInProvider(user.getSignInProvider())
			.username(user.getEmail())
			.build();
		
		return principal;
	}

}
