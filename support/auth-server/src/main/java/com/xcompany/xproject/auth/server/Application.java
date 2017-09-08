package com.xcompany.xproject.auth.server;
import java.security.Principal;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
@EnableAuthorizationServer
// Registe To Eureka
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
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
		return user;
	}
	
	@Configuration
	@EnableAuthorizationServer
	protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

//		@Autowired
//		private ResourceLoader resourceLoader;
		@Autowired
		@Qualifier("authenticationManagerBean")
		private AuthenticationManager authenticationManager;
		@Autowired
        private DataSource dataSource;
		/*@Autowired
		private Environment environment;*/
		
		/*https://stackoverflow.com/questions/34170281/spring-boot-oauth2-with-jdbc-token-store-gives-oauth-access-token-relation-doesn
		 * http://blog.csdn.net/haiyan_qi/article/details/52384734
		 * http://blog.csdn.net/neosmith/article/details/52539927
		 * https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
		 */
		@Bean 
        public TokenStore tokenStore() {
            return new JdbcTokenStore(dataSource);
        }
        @Bean 
        public ClientDetailsService clientDetails() {
            return new JdbcClientDetailsService(dataSource);
        }
        @Bean
        @Primary
        public DefaultTokenServices tokenServices() {
            DefaultTokenServices tokenServices = new DefaultTokenServices();
            tokenServices.setTokenStore(tokenStore());
	        tokenServices.setSupportRefreshToken(true);
	        tokenServices.setClientDetailsService(clientDetails());
	        tokenServices.setAccessTokenValiditySeconds( (int) TimeUnit.DAYS.toSeconds(30)); // 30 Day
            return tokenServices;
        }
		// Default: InMemoryTokenStore, endpoints.authenticationManager(authenticationManager) source code
		@Override
		public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
			endpoints.authenticationManager(authenticationManager)
				.tokenStore(tokenStore())
				.tokenServices(tokenServices())
				.setClientDetailsService(clientDetails());
		}
		
		@Override
	    public void configure(AuthorizationServerSecurityConfigurer oauthServer) 
	      throws Exception {
	        oauthServer
	          .tokenKeyAccess("permitAll()")
	          .checkTokenAccess("isAuthenticated()");
	    }
		
		/*@Override  
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {  
			
			TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();  
		    tokenEnhancerChain.setTokenEnhancers(  
		      Arrays.asList(tokenEnhancer(), accessTokenConverter()));  
			
	        endpoints.tokenStore(tokenStore())  
	                 .accessTokenConverter(accessTokenConverter())  
	                 .tokenEnhancer(tokenEnhancerChain)  
	                 .authenticationManager(authenticationManager);  
	    }  
	   
	    @Bean  
	    public TokenStore tokenStore() {  
	        return new JwtTokenStore(accessTokenConverter());  
	    }  
	    
	    @Bean  
	    public TokenEnhancer tokenEnhancer() {  
	        return new CustomTokenEnhancer();  
	    }  
	   
	    @Bean  
	    public JwtAccessTokenConverter accessTokenConverter() {  
	    	String tokenKey = environment.getProperty("security.oauth2.token.signing.key");
	        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();  
	        converter.setSigningKey(tokenKey);  
	        return converter;
	        
	    	String keyPass = environment.getProperty("security.oauth2.token.key.password");
	    	//String storePass = environment.getProperty("security.oauth2.token.store.password");
	    	String signingKey = environment.getProperty("security.oauth2.token.signingkey.password");
	        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
            KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("key/jwtkeystore.jks"), keyPass.toCharArray())
                    .getKeyPair(signingKey);
            // /opt/java/workspace/xproject/config/key /opt/java/workspace/xproject/config/key/jwtkeystore.jks
            KeyPair keyPair = new KeyStoreKeyFactory(new FileSystemResource("/opt/java/workspace/xproject/config/key/jwtkeystore.jks"), keyPass.toCharArray())
            .getKeyPair(signingKey);
            KeyPair keyPair = new KeyStoreKeyFactory(resourceLoader.getResource("classpath:key/jwtkeystore.jks"), keyPass.toCharArray())
            .getKeyPair(signingKey);
            converter.setKeyPair(keyPair);
            return converter;
	    }  
	   
	    @Bean  
	    @Primary  
	    public DefaultTokenServices tokenServices() {  
	        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();  
	        defaultTokenServices.setTokenStore(tokenStore());  
	        defaultTokenServices.setSupportRefreshToken(true);  
	        return defaultTokenServices;  
	    }  */
		
		@Override
		public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
			//clients.inMemory()
			clients.jdbc(dataSource)
				.withClient("acme")
				.secret("acmesecret")
				.authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials")
				.scopes("webshop");
		}
	}
	
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
