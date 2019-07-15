package com.sivale.test.jama_test.business.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sivale.test.jama_test.business.IUserBusiness;
import com.sivale.test.jama_test.exception.DuplicateEmailException;
import com.sivale.test.jama_test.form.RegistrationForm;
import com.sivale.test.jama_test.repository.UserDetailRepository;
import com.sivale.test.jama_test.repository.model.User;

@Service
public class UserBusinessImpl implements IUserBusiness {
	private static final Logger LOGGER = Logger.getLogger(UserBusinessImpl.class);
	
	 private PasswordEncoder passwordEncoder;
	 
	private UserDetailRepository repository;
	
	@Autowired
	public UserBusinessImpl(PasswordEncoder passwordEncoder, UserDetailRepository repository) {
		this.passwordEncoder = passwordEncoder;
		this.repository = repository;
	}
	
	@Override
	@Transactional
	public User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException {
		LOGGER.info(this.getClass().getSimpleName()+"_registerNewUserAccount_START");
		if (emailExists(userAccountData.getMail())) {
			LOGGER.info(this.getClass().getSimpleName()+"_registerNewUserAccount_DuplicateEmailException");
			throw new DuplicateEmailException("The email address: " + userAccountData.getMail() + " is already in use.");
		}
		String encodedPassword = encodePassword(userAccountData);
		User.Builder user = User.getBuilder()
			.email(userAccountData.getMail())
			.firstName(userAccountData.getFirstname())
			.lastName(userAccountData.getLastname())
			.password(encodedPassword);
			if (userAccountData.isSocialSignIn()) {
				user.signInProvider(userAccountData.getSignInProvider());
			}
	
		User registered = user.build();
		LOGGER.info(this.getClass().getSimpleName()+"_registerNewUserAccount_END");
		return repository.save(registered);
	}
	
	public Boolean emailExists(String email) {
		User user = repository.findByEmail(email);
		return user!=null;
	}
	
	private String encodePassword(RegistrationForm dto) {
		String encodedPassword = null;
		if (dto.isNormalRegistration()) {
			encodedPassword = passwordEncoder.encode(dto.getPassword());
		}
		return encodedPassword;
	}
	

}
