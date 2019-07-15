package com.sivale.test.jama_test.business;

import com.sivale.test.jama_test.exception.DuplicateEmailException;
import com.sivale.test.jama_test.form.RegistrationForm;
import com.sivale.test.jama_test.repository.model.User;

public interface IUserBusiness {
	
	User registerNewUserAccount(RegistrationForm userAccountData) throws DuplicateEmailException;

}
