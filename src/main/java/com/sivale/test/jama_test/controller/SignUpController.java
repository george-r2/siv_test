package com.sivale.test.jama_test.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SignUpController {
	private static final Logger LOGGER = Logger.getLogger(SignUpController.class);
	

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String redirectRequestToRegistrationPage() {
		LOGGER.info(this.getClass().getSimpleName()+"_redirectRequestToRegistrationPage_START");
		return "redirect:/user/register";
	}
}
