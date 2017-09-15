package com.xcompany.xproject.auth.server;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
//@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
		
//	 @Autowired
//     @Qualifier("authenticationManagerBean")
//     private AuthenticationManager authenticationManager;
     
	/*@Autowired
    private DataSource dataSource;*/
	
	/*@Bean 
    public TokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }*/
//    @Autowired
//    private TokenStore tokenStore;

//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources)
//            throws Exception {
//        resources.tokenStore(tokenStore());
//    }
	
//	@Autowired
//    RedisConnectionFactory redisConnectionFactory;
//    
//	@Bean
//	public TokenStore tokenStore() {
//	    return new RedisTokenStore(redisConnectionFactory);
//	}
//	
//    @Override
//    public void configure(ResourceServerSecurityConfigurer resources)
//            throws Exception {
//        resources.tokenStore(tokenStore());
//    }

//	@Override
//    public void configure(ResourceServerSecurityConfigurer resources) {
//        //resources.resourceId(DEMO_RESOURCE_ID).stateless(true);
//		//resources.stateless(true);
//		resources.authenticationManager(authenticationManager);
//    }
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
		//http.setSharedObject(AuthenticationManager.class, authenticationManager);
		// http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
        http
        	//.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        	//.and()
        	//.csrf().disable()
        	//.and()
        		.exceptionHandling()
                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
	        .and()
	        	.requestMatchers()
        		.antMatchers("/user")
            .and()
	        	.authorizeRequests()
	        	//.antMatchers("/login").anonymous()
	        	//.antMatchers("/login").permitAll()
	        	.anyRequest().authenticated();
//        http
//        .authorizeRequests()
//            .antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
//            .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
//            .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
//            .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
//            .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')")
//            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll();
    }
    
    /*@Bean
    @Primary
    public Boolean oauth2StatelessSecurityContext() {
        return Boolean.FALSE;
    }*/
}
