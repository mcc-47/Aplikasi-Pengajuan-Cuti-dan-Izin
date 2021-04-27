/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.services;

import com.mii.server.dto.AuthDto;
import com.mii.server.dto.LoginDto;
import com.mii.server.dto.UserDto;
import com.mii.server.entities.Role;
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
    
//    LOGIN SYSTEM
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
    
//    CRUD
    public List<User> listAll(){
        return userRepository.findAll();
    }
    
    public User getOne(Integer id){
        return userRepository.findById(id).get();
    }
    
    public UserDto create(UserDto user){
        User newUser = new User(
                user.getUserId(), 
                user.getUsername(), 
                passwordEncoder.encode(user.getPassword()));
        List<Role> role = new ArrayList<>();
        role.add(new Role(1));
        newUser.setRoleCollection(role);
        userRepository.save(newUser);
        return user;
    }
    
    public UserDto update(Integer id, UserDto user){
        User oldUser = userRepository.getOne(id);
        oldUser.setUsername(user.getUsername());
        oldUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(oldUser);
        return user;
    }
    
    public void delete(Integer id){
        System.out.println("pass delete user");
        User oldUser = getOne(id);
        userRepository.delete(oldUser);
        userRepository.deleteById(id);
        System.out.println("pass delete user");
    }
    
}
