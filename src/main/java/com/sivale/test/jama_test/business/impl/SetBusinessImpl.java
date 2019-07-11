package com.sivale.test.jama_test.business.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sivale.test.jama_test.business.ISetBusiness;

@Component
public class SetBusinessImpl implements ISetBusiness {

	
	@Override
	public Boolean isSuperSet(List<Integer> a, List<Integer> b) {
		//el conjunto vacio no es superconjunto de nadie
		if(isEmptySet(a)) {
			return false;
		}
		//el superconjunto no puede ser mas pequenio que su subconjunto
		if(a.size()<b.size()) {
			return false;
		}
		//en un mapa se almacenara el superconjunto
		Map<Integer,Integer> superSet = new HashMap<Integer, Integer>();
		for(int i:a) {
			superSet.put(i,0);
		}
		//ahora verificamos que todos los valores de b existan en a
		for(int i:b) {
			//si hay un elemento de b que no exista en a, entonces no es super conjunto
			if(!superSet.containsKey(i)) {
				return false;
			}
		}
		
		return true;
	}

	@Override
	public Boolean isSubSet(List<Integer> a, List<Integer> b) {
		//el subconjunto vacio es subconjunto de todos los demas
		if(isEmptySet(a)) {
			return true;
		}
		//el subconjunto no puede ser mas grande 
		if(a.size()>b.size())
			return false;
		//en un mapa almacenar un conjunto
		Map<Integer,Integer> set = new HashMap<Integer,Integer>();
		for(int i:b) {
			set.put(i,0);
		}
		//ahora todos los valores de a deben existir en b
		for(int i:a) {
			//si un valor de a no existe en el conjunto entonces a no es un subconjunto
			if(!set.containsKey(i)) {
				return false;
			}
		}
		return true;
	}
	
	public Boolean isEmptySet(List<Integer> set) {
		return set.size()==0;
	}

}
