package com.sivale.test.jama_test.utils;

import com.sivale.test.jama_test.beans.TError;
import com.sivale.test.jama_test.beans.TResponse;

public class Utils {
	
	public static TResponse createFailedResponse(String code,String message) {
		TResponse response = new TResponse();
		response.setSuccess(false);
		TError error = new TError();
		error.setCode(code);
		error.setMessage(message);
		response.setError(error);
		return response;
	}
}
