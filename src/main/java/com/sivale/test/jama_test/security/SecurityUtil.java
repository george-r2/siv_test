package com.sivale.test.jama_test.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sivale.test.jama_test.repository.model.User;
import com.sivale.test.jama_test.social.UserDetail;

public class SecurityUtil {
	public static void logInUser(User user) {
		UserDetail userDetails = UserDetail.getBuilder()
			.firstName(user.getFirstName())
			.id(user.getId())
			.lastName(user.getLastName())
			.password(user.getPassword())
			.role(user.getRole())
			.socialSignInProvider(user.getSignInProvider())
			.username(user.getEmail())
			.build();
		Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
}
