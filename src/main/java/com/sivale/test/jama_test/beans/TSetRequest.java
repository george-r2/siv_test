package com.sivale.test.jama_test.beans;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TSetRequest {
	@JsonProperty("a")
	private List<Integer> a;
	 @JsonProperty("b")
	private List<Integer> b;
	public List<Integer> getA() {
		return a;
	}
	public void setA(List<Integer> a) {
		this.a = a;
	}
	public List<Integer> getB() {
		return b;
	}
	public void setB(List<Integer> b) {
		this.b = b;
	}
	 
	 
}
