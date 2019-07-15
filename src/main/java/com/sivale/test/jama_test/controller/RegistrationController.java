package com.sivale.test.jama_test.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.sivale.test.jama_test.business.IUserBusiness;
import com.sivale.test.jama_test.exception.DuplicateEmailException;
import com.sivale.test.jama_test.form.RegistrationForm;
import com.sivale.test.jama_test.repository.model.User;
import com.sivale.test.jama_test.security.SecurityUtil;
import com.sivale.test.jama_test.social.SocialMediaService;

@Controller
@SessionAttributes("user")
public class RegistrationController {
	private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);
	 private IUserBusiness userBusiness;
//
	@Autowired
	public RegistrationController(IUserBusiness service) {
		this.userBusiness = service;
	}
	
	@RequestMapping(value = "/user/register", method = RequestMethod.GET)
	public String showRegistrationForm(WebRequest request, Model model) {
		LOGGER.info("mostrando la pagina de registro");
		Connection<?> connection = ProviderSignInUtils.getConnection(request);
//		ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils(null, null, null);
//		Connection<?> connection = providerSignInUtils.
		RegistrationForm registration = createRegistrationDTO(connection);
		LOGGER.info("Rendering registration form with information: {}"+ registration.toString());
		model.addAttribute("user", registration);
		return "user/registrationForm";
	}
	
	private RegistrationForm createRegistrationDTO(Connection<?> connection) {
		RegistrationForm dto = new RegistrationForm();
		if (connection != null) {
			UserProfile socialMediaProfile = connection.fetchUserProfile();
			dto.setMail(socialMediaProfile.getEmail());
			dto.setFirstname(socialMediaProfile.getFirstName());
			dto.setLastname(socialMediaProfile.getLastName());
			ConnectionKey providerKey = connection.getKey();
			dto.setSignInProvider(SocialMediaService.valueOf(providerKey.getProviderId().toUpperCase()));
		}
		return dto;
	}
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/user/register", method = RequestMethod.POST)
	public String registerUserAccount(@Valid @ModelAttribute("user") RegistrationForm userAccountData,
		BindingResult result,
		WebRequest request) throws DuplicateEmailException {
		LOGGER.info(this.getClass().getSimpleName()+"_showRegistrationForm_START");
		if (result.hasErrors()) {
			LOGGER.info(this.getClass().getSimpleName()+"_showRegistrationForm_hay errores");
			return "user/registrationForm";
		}
		User registered = createUserAccount(userAccountData, result);
		if (registered == null) {
			LOGGER.info(this.getClass().getSimpleName()+"_showRegistrationForm_no se registro");
			return "user/registrationForm";
		}
		SecurityUtil.logInUser(registered);
		ProviderSignInUtils.handlePostSignUp(registered.getEmail(), request);
		LOGGER.info(this.getClass().getSimpleName()+"_showRegistrationForm_END");
		return "redirect:/";
	}
	
	
//	private RegistrationForm createRegistrationDTO(Connection<?> connection) {
//		RegistrationForm dto = new RegistrationForm();
//	 
//		if (connection != null) {
//			UserProfile socialMediaProfile = connection.fetchUserProfile();
//			dto.setMail(socialMediaProfile.getEmail());
//			dto.setFirstname(socialMediaProfile.getFirstName());
//			dto.setLastname(socialMediaProfile.getLastName());
//			ConnectionKey providerKey = connection.getKey();
//			dto.setSignInProvider(SocialMediaService.valueOf(providerKey.getProviderId().toUpperCase()));
//		}
//		return dto;
//	}
	
	
	
	private User createUserAccount(RegistrationForm userAccountData, BindingResult result) {
		User registered = null;
		try {
			registered = userBusiness.registerNewUserAccount(userAccountData);
		}
		catch (DuplicateEmailException ex) {
			addFieldError(
				"user",
				"email",
				userAccountData.getMail(),
				"NotExist.user.email",
				result);
		}
		return registered;
	}
	
	private void addFieldError(String objectName, String fieldName, String fieldValue,  String errorCode, BindingResult result) {
		FieldError error = new FieldError(
			objectName,
			fieldName,
			fieldValue,
			false,
			new String[]{errorCode},
			new Object[]{},
			errorCode
		);
		result.addError(error);
	}
}
