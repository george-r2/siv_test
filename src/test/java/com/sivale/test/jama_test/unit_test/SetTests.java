package com.sivale.test.jama_test.unit_test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.sivale.test.jama_test.business.impl.SetBusinessImpl;

import junit.framework.Assert;
@SuppressWarnings("deprecation")
public class SetTests {
	
	SetBusinessImpl setBusiness;

	List<Integer> emptySet;
	List<Integer> setA;
	List<Integer> setB;
	
	@Before
	public void init() {
		setBusiness=new SetBusinessImpl();
		
		emptySet = new ArrayList<Integer>();
		setA = new ArrayList<Integer>();
		setA.add(1);
		setA.add(2);
		setA.add(3);
		setB = new ArrayList<Integer>();
		setB.add(1);
		setB.add(2);
		setB.add(3);
		setB.add(4);
	}
	
	@Test
	public void superset_test_A_B() {
		Boolean expected = false;
		Boolean gotten = setBusiness.isSuperSet(setA, setB);
		Assert.assertEquals(expected, gotten);
	}
	
	@Test
	public void superset_test_B_A() {
		Boolean expected = true;
		Boolean gotten = setBusiness.isSuperSet(setB, setA);
		Assert.assertEquals(expected, gotten);
	}
	
	@Test
	public void subset_test_A_B() {
		Boolean expected = true;
		Boolean gotten = setBusiness.isSubSet(setA, setB);
		Assert.assertEquals(expected, gotten);
	}
	
	@Test
	public void subset_test_B_A() {
		Boolean expected = false;
		Boolean gotten = setBusiness.isSubSet(setB, setA);
		Assert.assertEquals(expected, gotten);
	}
	
	@Test
	public void superset_test_A_A() {
		Boolean expected = true;
		Boolean gotten = setBusiness.isSuperSet(setA, setA);
		Assert.assertEquals(expected, gotten);
	}
	
	@Test
	public void subset_test_A_A() {
		Boolean expected = true;
		Boolean gotten = setBusiness.isSubSet(setA, setA);
		Assert.assertEquals(expected, gotten);
	}
	
	@Test
	public void superset_test_empty_set_1() {
		List<Integer> set_a = new ArrayList<Integer>();
		set_a.add(1);set_a.add(3);set_a.add(4);set_a.add(7);set_a.add(10);
		List<Integer> set_b = new ArrayList<Integer>();
		Boolean expected = true;
		Boolean gotten = setBusiness.isSuperSet(set_a, set_b);
		Assert.assertEquals(expected, gotten);
	}
	
	@Test
	public void superset_test_empty_set_2() {
		List<Integer> set_a = new ArrayList<Integer>();
		set_a.add(1);set_a.add(3);set_a.add(4);set_a.add(7);set_a.add(10);
		List<Integer> set_b = new ArrayList<Integer>();
		Boolean expected = false;
		Boolean gotten = setBusiness.isSuperSet(set_b, set_a);
		Assert.assertEquals(expected, gotten);
	}
	
	
	
}
