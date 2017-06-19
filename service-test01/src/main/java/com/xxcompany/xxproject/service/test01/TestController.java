package com.xxcompany.xxproject.service.test01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
	@Autowired
    private DiscoveryClient client;
	@Autowired
	private Environment environment;
	
	@RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public Integer add(@RequestParam Integer a, @RequestParam Integer b) {
        @SuppressWarnings("deprecation")
		ServiceInstance instance = client.getLocalServiceInstance();
        Integer r = a + b;
        LOGGER.info("/add, host:" + instance.getHost() + ", port:" + instance.getPort() + ", service_id:" + instance.getServiceId() + ", result:" + r);
        return r;
    }
	
	@RequestMapping(value = "/test" ,method = RequestMethod.GET)
	public String test() {
		String test = environment.getProperty("test");
		if (test.equals("AAA-BBB-CCC-DDD")) {
			throw new RuntimeException("SOME ERROR");
		}
		return environment.getProperty("test");
	}
}
