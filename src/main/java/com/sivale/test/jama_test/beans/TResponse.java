package com.sivale.test.jama_test.beans;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TResponse {

	private Boolean success;
	private TError error;
	private TResult result;
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public TError getError() {
		return error;
	}
	public void setError(TError error) {
		this.error = error;
	}
	public TResult getResult() {
		return result;
	}
	public void setResult(TResult result) {
		this.result = result;
	}
	
	
	
}
