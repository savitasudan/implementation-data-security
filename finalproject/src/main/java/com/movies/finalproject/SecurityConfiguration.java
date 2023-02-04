/*package com.movies.finalproject;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       //set my configuration

       auth.inMemoryAuthentication()
       .withUser("librarian")
       .password("password")
       .roles("Library")
       .and()
       .withUser("admin")
       .password("password")
       .roles("Admin");

    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
       .antMatchers("/admin").hasAnyRole("Admin","Library")
       .and().formLogin();
    }
    
    
}
*/