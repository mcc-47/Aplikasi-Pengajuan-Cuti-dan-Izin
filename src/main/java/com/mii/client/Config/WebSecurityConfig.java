/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Config;

//import org.springframework.context.annotation.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;


/**
 *
 * @author User
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**","/js/**").permitAll()
                .antMatchers("/manager/**").hasRole("MANAGER")
                .antMatchers("/request/**").hasRole("EMPLOYEE")
                .antMatchers("/holiday/**","/mandatory-leave/**").hasRole("ADMINHR")
                .antMatchers("/dashboard", "/employee/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login").loginProcessingUrl("/login")
                .failureForwardUrl("/login?error")
                .successForwardUrl("/dashboard")
                .permitAll();
    }
    
        @Bean
    public SpringSecurityDialect securityDialect() {
        return new SpringSecurityDialect();
    }
}

