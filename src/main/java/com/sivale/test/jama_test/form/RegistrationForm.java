package com.sivale.test.jama_test.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.sivale.test.jama_test.social.SocialMediaService;
/**
 * 
 * @author george_r2
 *
 */
public class RegistrationForm {

	@NotEmpty
	@Size(max=100)
	private String mail;
	
	@NotEmpty
	@Size(max=100)
	private String firstname;
	
	@NotEmpty
	@Size(max=100)
	private String lastname;
	
	private String password;
	
	private String passwordVerification;
	
	private SocialMediaService signInProvider;
	
	public boolean isNormalRegistration() {
		 return signInProvider == null;
	}
	 
	public boolean isSocialSignIn() {
		return signInProvider != null;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordVerification() {
		return passwordVerification;
	}

	public void setPasswordVerification(String passwordVerification) {
		this.passwordVerification = passwordVerification;
	}

	public SocialMediaService getSignInProvider() {
		return signInProvider;
	}

	public void setSignInProvider(SocialMediaService signInProvider) {
		this.signInProvider = signInProvider;
	}
	
	
}
