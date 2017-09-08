package com.xcompany.xproject.auth.server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
//@Order(ManagementServerProperties.ACCESS_OVERRIDE_ORDER)
public class CustomWebSecurityConfigurer extends WebSecurityConfigurerAdapter {
	@Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	http.authorizeRequests()
    		.antMatchers("/login").permitAll()
    		.anyRequest().authenticated()
    		.and()
            .formLogin().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("passw0rd").roles("USER").authorities("FOO_READ")
                .and()
                .withUser("admin").password("passw0rd").roles("ADMIN").authorities("FOO_READ", "FOO_WRITE");
//        auth.parentAuthenticationManager(authenticationManager);
    }
}

