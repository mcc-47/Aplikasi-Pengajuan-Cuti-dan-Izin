/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Models;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Fadel
 */
@Data
public class AuthResponse {
     
    private Integer userId;
    private List<String> authorities;

    public AuthResponse(Integer userId, List<String> authorities) {
        this.userId = userId;
        this.authorities = authorities;
    }

    public AuthResponse() {
    }
}
