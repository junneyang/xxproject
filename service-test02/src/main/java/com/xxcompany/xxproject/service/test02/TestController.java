package com.xxcompany.xxproject.service.test02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(TestController.class);
	
//	@Autowired
//    RestTemplate restTemplate;
	
	@Autowired
    Test01UtilsService test01UtilsService;
	
//	@RequestMapping(value = "/add", method = RequestMethod.GET)
//    public String add() {
//        return restTemplate.getForEntity("http://SERVICE-TEST01/add?a=10&b=20", String.class).getBody();
//    }
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
    public Integer add_feign(@RequestParam Integer a, @RequestParam Integer b) {
        return test01UtilsService.add(a, b);
    }
}
