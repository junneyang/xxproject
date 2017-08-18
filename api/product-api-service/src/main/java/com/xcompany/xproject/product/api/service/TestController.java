package com.xcompany.xproject.product.api.service;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
//	@Autowired
//    private DiscoveryClient client;
//	@Autowired
//	private Environment environment;
//	@Autowired
//	private RestTemplate restTemplate;
	@Autowired
	private HystrixProductApiService hystrixProductApiService;


	@RequestMapping(value = "/product-api-composite" ,method = RequestMethod.GET)
	public List<Object> test(@RequestHeader(value="Authorization") String authorizationHeader, Principal currentUser) {
		
		LOGGER.info("User={}, Auth={}", currentUser.getName(), authorizationHeader);
		
		List<Object> retList = hystrixProductApiService.testProductApiService();
		LOGGER.info(retList.toString());
		return retList;
	}
	
	@RequestMapping(value = "/product-api-test" ,method = RequestMethod.GET)
	public String testProduct(@RequestHeader(value="Authorization") String authorizationHeader, Principal currentUser) {

		LOGGER.info("User={}, Auth={}", currentUser.getName(), authorizationHeader);
		
		String ret = hystrixProductApiService.testProductApiService0();
		LOGGER.info(ret);
		return ret;
	}
	
}

