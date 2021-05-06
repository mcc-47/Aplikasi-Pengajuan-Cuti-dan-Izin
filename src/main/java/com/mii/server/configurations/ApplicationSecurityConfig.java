/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author jakab
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter{
    
    @Autowired
    PasswordConfig passwordConfig;
    
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(passwordConfig.authenticationProvider());
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/api/mandatory-leave","/api/holiday").hasAnyRole("ADMINHR","EMPLOYEE")
//                .antMatchers(HttpMethod.GET, "/api/employee").hasRole("ADMINHR")
//                .antMatchers(HttpMethod.GET, "/api/employee/**").hasAnyRole("ADMINHR","MANAGER","EMPLOYEE")
//                .antMatchers(HttpMethod.POST, "/api/employee").hasRole("ADMINHR")
//                .antMatchers(HttpMethod.PUT, "/api/employee/**").hasAnyRole("ADMINHR","EMPLOYEE")
//                .antMatchers(HttpMethod.DELETE, "/api/employee/**").hasRole("ADMINHR")
//                .antMatchers(HttpMethod.GET, "/api/request").hasAnyRole("ADMINHR","MANAGER")
//                .antMatchers(HttpMethod.GET, "/api/request/**").hasAnyRole("MANAGER","EMPLOYEE")
//                .antMatchers(HttpMethod.POST, "/api/request").hasRole("EMPLOYEE")
//                .antMatchers(HttpMethod.PUT, "/api/request/**").hasRole("MANAGER")
//                .antMatchers(HttpMethod.DELETE, "/api/request/**").hasAnyRole("ADMINHR","MANAGER","EMPLOYEE")
//                .antMatchers(HttpMethod.GET, "/api/manager").hasAnyRole("ADMINHR","MANAGER")
//                .antMatchers(HttpMethod.GET, "/api/manager/**").hasAnyRole("MANAGER","EMPLOYEE")
//                .antMatchers(HttpMethod.GET, "/api/manager/**").hasAuthority("EMPLOYEE_READ")
//                .antMatchers(HttpMethod.POST, "/api/manager").hasRole("EMPLOYEE")
//                .antMatchers(HttpMethod.PUT, "/api/manager/**").hasRole("MANAGER")
//                .antMatchers(HttpMethod.DELETE, "/api/manager/**").hasAnyRole("ADMINHR","MANAGER","EMPLOYEE")
                .antMatchers("/login").permitAll()
                .antMatchers("/api/user/login").permitAll()
                .and()
                .logout().disable()
                .formLogin().disable()
                .httpBasic();
    }
}
