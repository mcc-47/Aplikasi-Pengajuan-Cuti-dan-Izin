/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Service;

import com.mii.client.Models.AuthRequest;
import com.mii.client.Models.AuthResponse;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Fadel
 */
@Service
public class AuthService {
    
    @Autowired
    private RestTemplate restTemplate;

    @Value("${api.url}/api/user/login")
    private String url;

    public boolean loginProcess(AuthRequest request) {
        boolean isLoginSuccess = false;
        /* use try catch for error handling */
        try {
            HttpEntity entity = new HttpEntity(request);
            ResponseEntity<AuthResponse> response = restTemplate
                    .exchange(url, HttpMethod.POST, entity,
                            new ParameterizedTypeReference<AuthResponse>() {
                    });
            
            /* call method for set session */
            setAuthorization(request.getUsername(), request.getPassword(), 
                    response.getBody().getAuthorities());
            
            isLoginSuccess = true;
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        
        return isLoginSuccess;
    }
    
    /* Set spring security session */
    private void setAuthorization(String username, String password, List<String> authorities) {
        UsernamePasswordAuthenticationToken authToken = 
                new UsernamePasswordAuthenticationToken(username, password, getListAuthorities(authorities));
        
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
    
    /* Set list of authothorities */
    private List<GrantedAuthority> getListAuthorities(List<String> authorities) {
        return authorities.stream()
                .map(auth -> new SimpleGrantedAuthority(auth))
                .collect(Collectors.toList());
    }
}
