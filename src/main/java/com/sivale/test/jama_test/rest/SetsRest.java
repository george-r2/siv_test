package com.sivale.test.jama_test.rest;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sivale.test.jama_test.beans.TResponse;
import com.sivale.test.jama_test.beans.TSetRequest;
import com.sivale.test.jama_test.beans.TSetResult;
import com.sivale.test.jama_test.business.ISetBusiness;
import com.sivale.test.jama_test.utils.Utils;

@RestController
@RequestMapping("test/")
public class SetsRest {

	@Autowired
	private ISetBusiness setBusiness;
	
	private static final Logger LOGGER = Logger.getLogger(SetsRest.class);
	
	@PostMapping("superset")
	public ResponseEntity<TResponse> isSuperSet(@RequestBody TSetRequest request ){
		LOGGER.info(this.getClass().getSimpleName()+"_isSuperSet_START");
		TResponse response;
		try {
			response = new TResponse();
			TSetResult result = new TSetResult();
			Boolean is = setBusiness.isSuperSet(request.getA(), request.getB());
			result.setIsSuperSet(is);
			response.setResult(result);
		}catch(Exception e) {
			LOGGER.error("Error al verificar si es un superconjunto",e);
			response = Utils.createFailedResponse(e.getClass().getSimpleName(), e.getMessage());
		}
		ResponseEntity<TResponse> re = new ResponseEntity<TResponse>(response,HttpStatus.OK);
		return re;
	}
	
	@PostMapping("subset")
	public ResponseEntity<TResponse> isSubSet(@RequestBody TSetRequest request ){
		LOGGER.info(this.getClass().getSimpleName()+"_isSubSet_START");
		TResponse response;
		try {
			response = new TResponse();
			TSetResult result = new TSetResult();
			Boolean is = setBusiness.isSubSet(request.getA(), request.getB());
			result.setIsSubSet(is);
			response.setResult(result);
		}catch(Exception e) {
			LOGGER.error("Error al verificar si es un subconjunto",e);
			response = Utils.createFailedResponse(e.getClass().getSimpleName(), e.getMessage());
		}
		ResponseEntity<TResponse> re = new ResponseEntity<TResponse>(response,HttpStatus.OK);
		return re;
	}
	
}
