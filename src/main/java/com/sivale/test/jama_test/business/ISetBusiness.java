package com.sivale.test.jama_test.business;

import java.util.List;

public interface ISetBusiness {
	/**
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	Boolean isSuperSet(List<Integer> a,List<Integer> b);
	
	Boolean isSubSet(List<Integer> a,List<Integer> b);
}
