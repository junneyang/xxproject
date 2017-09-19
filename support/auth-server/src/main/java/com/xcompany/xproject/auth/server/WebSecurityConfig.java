package com.xcompany.xproject.auth.server;

import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;

import com.xcompany.xproject.auth.server.model.Role;
import com.xcompany.xproject.auth.server.model.RoleRepository;
import com.xcompany.xproject.auth.server.model.User;
import com.xcompany.xproject.auth.server.model.UserRepository;

@Configuration
//@EnableWebSecurity
//@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
//@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
@Order(1)
//ENCODE: https://stackoverflow.com/questions/28838530/spring-boot-with-security-oauth2-how-to-use-resource-server-with-web-login-for
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    private DataSource dataSource;
	@Autowired
    private CustomUserDetailsService userDetailsService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

	@Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
	
//	@Override
//    public void configure(WebSecurity web) {
//	    web.ignoring().
//	    	antMatchers("/user");
//    }
	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	//http.csrf().disable();
    	//http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	//http.headers().cacheControl();
    	http.formLogin().permitAll()
    		.and()
				.requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
			.and()
				.formLogin().loginPage("/login")
	    	.and()
		    	.authorizeRequests()
		    	.antMatchers("/login").permitAll()
		    	.anyRequest().authenticated()
		    .and()
		    	.httpBasic().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    	auth.userDetailsService(userDetailsService);
        auth.jdbcAuthentication().dataSource(dataSource);
        User user =  new User();
        user.setName("admin");
        user.setPassword("passw0rd");
        user.setLogin("admin");
        user.setEmail("597092663@qq.com");
        user.setPlatform(123456);
        Collection<Role> roles = new ArrayList<Role>();
        Role role = new Role();
        role.setName("ADMIN");
        roles.add(role);
		user.setRoles(roles);
		
		roleRepository.save(role);
		userRepository.save(user);
        	//.withDefaultSchema()
//        	.withUser("user").password("passw0rd").roles("USER").authorities("FOO_READ")
//        	.and()
//        	.withUser("admin").password("passw0rd").roles("ADMIN").authorities("FOO_READ", "FOO_WRITE");
        /*auth.inMemoryAuthentication()
                .withUser("user").password("passw0rd").roles("USER").authorities("FOO_READ")
                .and()
                .withUser("admin").password("passw0rd").roles("ADMIN").authorities("FOO_READ", "FOO_WRITE");*/
        //auth.parentAuthenticationManager(authenticationManager);
    }
}

