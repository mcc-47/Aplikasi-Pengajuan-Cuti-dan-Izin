/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.dto;

import java.util.List;

/**
 *
 * @author jakab
 */
public class AuthDto {
    
    private Integer userId;
    private String username;
    private List<String> authorities;

    public AuthDto() {
    }

    public AuthDto(Integer userId, String username, List<String> authorities) {
        this.userId = userId;
        this.username = username;
        this.authorities = authorities;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> authorities) {
        this.authorities = authorities;
    }
    
}
