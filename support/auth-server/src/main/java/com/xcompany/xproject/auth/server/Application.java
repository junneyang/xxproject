package com.xcompany.xproject.auth.server;
import java.security.Principal;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//https://github.com/Baeldung/spring-security-oauth
//https://github.com/spring-projects/spring-security-oauth/issues/703
@SpringBootApplication
@RestController
////@EnableResourceServer
////@EnableAuthorizationServer
//// Registe To Eureka
@EnableDiscoveryClient
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
	
//	@Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/login").setViewName("login");
//        registry.addViewController("/oauth/confirm_access").setViewName("authorize");
//    }
	
	
	@PreAuthorize("#oauth2.hasScope('webshop')")
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
//	@Autowired
//    private TokenStore tokenStore;
//	@RequestMapping("/userinfo")
//    public Map<String, Object> getExtraInfo(OAuth2Authentication auth) {
//        final OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) auth.getDetails();
//        final OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
//        System.out.println(accessToken);
//        return accessToken.getAdditionalInformation();
//    }


	/*@Inject
	TraceFilter traceFilter;

	@Bean
	public FilterRegistrationBean myTraceFilter() {
	    LOGGER.info("Register a TraceFilter with HIGHEST_PRECEDENCE");
	    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(traceFilter, new ServletRegistrationBean[0]);
	    filterRegistrationBean.setDispatcherTypes(DispatcherType.ASYNC, new DispatcherType[]{DispatcherType.ERROR, DispatcherType.FORWARD, DispatcherType.INCLUDE, DispatcherType.REQUEST});
	    filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
	    return filterRegistrationBean;
	}*/

}
