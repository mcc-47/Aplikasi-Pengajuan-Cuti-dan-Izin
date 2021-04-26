/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.services;

import com.mii.server.dto.AuthDto;
import com.mii.server.dto.LoginDto;
import com.mii.server.entities.User;
import com.mii.server.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author jakab
 */
@Service
public class UserService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;
    
    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    
    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user==null) {
            throw new UnsupportedOperationException("User Name NOT FOUND");
        }
        return user;
    }
    
    public AuthDto loginUserByPassword(LoginDto loginDto)throws Exception{
        User user = loadUserByUsername(loginDto.getUsername());
        if (!(passwordEncoder.matches(loginDto.getPassword(), user.getPassword()))) {
            throw new Exception("Wrong Pasword");
        }
        //untuk setting session dan atau cookies
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                                            loginDto.getUsername(),        
                                                            loginDto.getPassword(), 
                                                            user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        List<String> grantedAuth = new ArrayList<>();
        for (GrantedAuthority auth : user.getAuthorities()) {
            grantedAuth.add(auth.getAuthority());
        }
        return new AuthDto(user.getUserId(), user.getUsername(), grantedAuth);
    }
    
}
