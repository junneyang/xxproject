package com.xcompany.xproject.product.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
    private DiscoveryClient client;
	@Autowired
	private Environment environment;


	@RequestMapping(value = "/product" ,method = RequestMethod.GET)
	public String test() {
		String serviceName = environment.getProperty("spring.application.name");
		LOGGER.info(serviceName);
		return serviceName;
	}
}

