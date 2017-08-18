package com.xcompany.xproject.product.composite.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
	private HystrixProductCompositeService hystrixProductCompositeService;


	@RequestMapping(value = "/product-composite" ,method = RequestMethod.GET)
	public List<String> test() {

		List<String> retList = hystrixProductCompositeService.productCompostieService();
		LOGGER.info(retList.toString());
		return retList;
	}
	
	@RequestMapping(value = "/product-test" ,method = RequestMethod.GET)
	public String testProduct() {

		String ret = hystrixProductCompositeService.testProductService();
		LOGGER.info(ret);
		return ret;
	}
	
}

