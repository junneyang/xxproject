package com.xcompany.xproject.product.composite.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HystrixProductCompositeService {
	private static final Logger LOGGER = LoggerFactory.getLogger(HystrixProductCompositeService.class);
	
	@Autowired
	private RestTemplate restTemplate;

	@HystrixCommand(fallbackMethod = "productCompostieServiceFallback")
	public List<String> productCompostieService() {
		// TODO Auto-generated method stub
		String product = restTemplate.getForEntity("http://PRODUCT-SERVICE/product", String.class).getBody();
		String review = restTemplate.getForEntity("http://REVIEW-SERVICE/review", String.class).getBody();
		List<String> retList = new ArrayList<String>();
		retList.add(product);
		retList.add(review);
		LOGGER.info(retList.toString());
		return retList;
	}

	public List<String> productCompostieServiceFallback() {
		// TODO Auto-generated method stub
		return Arrays.asList("productCompostieServiceFallback-error");
	}
	
	@HystrixCommand(fallbackMethod = "testProductServiceFallback")
	public String testProductService() {
		// TODO Auto-generated method stub
		String product = restTemplate.getForEntity("http://PRODUCT-SERVICE/product", String.class).getBody();
		return product;
	}

	public String testProductServiceFallback() {
		// TODO Auto-generated method stub
		return "testProductServiceFallback-error";
	}

}
