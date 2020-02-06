package com.embl.person.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
	/**
	 * security configuration 
	 * 
	 */
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
    	//authorized access to the h2-console. ex: http://localhost:8080/h2-console/
        httpSecurity.authorizeRequests().antMatchers("/").permitAll().and()
                .authorizeRequests().antMatchers("/h2-console/**").permitAll();
        
        // authentication for rest api which validates basic authentication 
        httpSecurity.csrf().disable();
        httpSecurity.headers().frameOptions().disable();
        httpSecurity.csrf().disable()
        .authorizeRequests().antMatchers("/api/v1/**").authenticated()
        .and()
        .httpBasic();
    }
    /**
     * Authorization method for api call
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) 
            throws Exception 
    {
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password("{noop}password")
            .roles("USER");
    }
}
