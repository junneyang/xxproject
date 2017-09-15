package com.xcompany.xproject.auth.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	@Autowired
	@Qualifier("authenticationManagerBean")
	private AuthenticationManager authenticationManager;
	
	@Autowired
    RedisConnectionFactory redisConnectionFactory;
	
//	@Autowired
//    private DataSource dataSource;
//	
//	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//	/*@Autowired
//	private Environment environment;*/
//	/*@Autowired
//	private CustomUserDetailsService userDetailsService;*/
//	
//	/*https://stackoverflow.com/questions/34170281/spring-boot-oauth2-with-jdbc-token-store-gives-oauth-access-token-relation-doesn
//	 * http://blog.csdn.net/haiyan_qi/article/details/52384734
//	 * http://blog.csdn.net/neosmith/article/details/52539927
//	 * https://github.com/spring-projects/spring-security-oauth/blob/master/spring-security-oauth2/src/test/resources/schema.sql
//	 */
//	@Bean 
//    public TokenStore tokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }
//	@Bean
//	protected AuthorizationCodeServices authorizationCodeServices() {
//		return new JdbcAuthorizationCodeServices(dataSource);
//	}
//	@Bean
//    public ApprovalStore approvalStore() {
//        return new JdbcApprovalStore(dataSource);
//    }
//    @Bean 
//    public ClientDetailsService clientDetails() {
//        return new JdbcClientDetailsService(dataSource);
//    }
//    
//    @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(tokenStore());
//        tokenServices.setSupportRefreshToken(true);
//        tokenServices.setClientDetailsService(clientDetails());
//        tokenServices.setAccessTokenValiditySeconds( (int) TimeUnit.DAYS.toSeconds(30)); // 30 Day
//        return tokenServices;
//    }
//	// Default: InMemoryTokenStore, endpoints.authenticationManager(authenticationManager) source code
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.authenticationManager(authenticationManager)
//			.tokenStore(tokenStore())
//			.authorizationCodeServices(authorizationCodeServices())
//			.approvalStore(approvalStore())
//			.tokenServices(tokenServices())
//			//.userDetailsService(userDetailsService)
//			.setClientDetailsService(clientDetails());
//	}
	
//	@Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) 
//      throws Exception {
//        oauthServer
//        	.passwordEncoder(passwordEncoder)
//        	.tokenKeyAccess("permitAll()")
//        	.checkTokenAccess("isAuthenticated()");
//        // NB!!!!!!!!!!!!!!!!!!!!!!!!
//        //oauthServer.allowFormAuthenticationForClients();
//    }
	
	/*@Bean
    @Primary
	public void configure(AuthorizationServerSecurityConfigurer allowFormAuthenticationForClients) {
		allowFormAuthenticationForClients.
	}*/
	
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
	
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		//clients.inMemory()
//		clients.jdbc(dataSource).passwordEncoder(passwordEncoder)
//			.withClient("acme")
//			.secret("acmesecret")
//			.authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials")
//			.scopes("webshop");
//	}
	
	@Bean
	public TokenStore tokenStore() {
	    return new RedisTokenStore(redisConnectionFactory);
	}
	
	@Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager);
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
        	//.allowFormAuthenticationForClients()
        	.tokenKeyAccess("permitAll()")
        	.checkTokenAccess("isAuthenticated()");
    }
    
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
        .withClient("acme")
		.secret("acmesecret")
		.authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials")
		.scopes("webshop");
    }
	
}
