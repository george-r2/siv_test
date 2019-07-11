package com.sivale.test.jama_test.social.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

import com.sivale.test.jama_test.repository.UserDetailRepository;
import com.sivale.test.jama_test.social.UserDetail;

public class SocialUserDetailsServiceImpl implements SocialUserDetailsService {
	
	private UserDetailsServiceImpl userDetailService;
	
	@Autowired
	public SocialUserDetailsServiceImpl(UserDetailsServiceImpl userDetailService) {
		this.userDetailService = userDetailService;
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
		 UserDetails userDetails = userDetailService.loadUserByUsername(userId);
		 return (SocialUserDetails) userDetails;
	}

}
