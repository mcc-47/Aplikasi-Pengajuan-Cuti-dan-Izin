/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.controllers;

import com.mii.server.dto.AuthDto;
import com.mii.server.dto.LoginDto;
import com.mii.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jakab
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    
    @Autowired
    UserService userService;
    
    @PostMapping("/login")
    public AuthDto loginUser(@RequestBody LoginDto loginDto) throws Exception{
        return userService.loginUserByPassword(loginDto);
    }
    
}
