package com.xcompany.xproject.product.api.service;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
//https://spring.io/blog/2015/11/30/migrating-oauth2-apps-from-spring-boot-1-2-to-1-3
//http://presos.dsyer.com/decks/microservice-security.html#slide23
//https://stackoverflow.com/questions/32709473/no-enableoauth2resource-in-spring-cloud-security-1-1-invalid-access-token-afte
//https://github.com/spring-cloud/spring-cloud-security
//There is no @EnableOAuth2Resource annotation in Spring Cloud 1.1. You just use the regular @EnableResourceServer from Spring OAuth.
//@EnableOAuth2Resource
@EnableResourceServer
public class Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//SpringApplication.run(Application.class, args);
		new SpringApplicationBuilder(Application.class).web(true).run(args);
	}
	
	@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                LOGGER.debug(beanName);
            }
        };
    }
	
	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
