package com.sivale.test.jama_test.social.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sivale.test.jama_test.repository.UserDetailRepository;
import com.sivale.test.jama_test.repository.model.User;
import com.sivale.test.jama_test.social.UserDetail;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	private static final Logger LOGGER = Logger.getLogger(UserDetailsServiceImpl.class);
	
	private UserDetailRepository userRepo;
	
	@Autowired
	public UserDetailsServiceImpl(UserDetailRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGGER.info(this.getClass().getSimpleName()+"_loadUserByUsername_START");
		User user = userRepo.findByEmail(username);
		if (user == null) {
			LOGGER.info(this.getClass().getSimpleName()+"_loadUserByUsername_UsernameNotFoundException");
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
