package com.xcompany.xproject.product.api.service;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HystrixProductApiService {
	private static final Logger LOGGER = LoggerFactory.getLogger(HystrixProductApiService.class);
	
	@Autowired
	private RestTemplate restTemplate;


	@HystrixCommand(fallbackMethod = "testProductApiServiceFallback")
	public List<Object> testProductApiService() {
		// TODO Auto-generated method stub
		//restTemplate.ge
		Object[] product_api_composite = restTemplate.getForEntity("http://PRODUCT-COMPOSITE-SERVICE/product-composite", Object[].class).getBody();
		List<Object> retList = Arrays.asList(product_api_composite);
		LOGGER.info(retList.toString());
		return retList;
	}

	public List<Object> testProductApiServiceFallback() {
		// TODO Auto-generated method stub
		return Arrays.asList("testProductApiServiceFallback-error");
	}
	
	//@HystrixCommand(fallbackMethod = "testProductApiServiceFallback0")
	public String testProductApiService0() {
		// TODO Auto-generated method stub 
		String product_api_composite = restTemplate.getForEntity("http://PRODUCT-COMPOSITE-SERVICE/product-test", String.class).getBody();
		LOGGER.info(product_api_composite);
		return "product_api_composite0: " + product_api_composite;
		
	}

	public String testProductApiServiceFallback0() {
		// TODO Auto-generated method stub
		return "testProductApiServiceFallback0-error";
	}

}

