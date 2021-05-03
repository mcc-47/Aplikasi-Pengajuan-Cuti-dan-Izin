/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Service;

import com.mii.client.Config.RequestFormat;
import com.mii.client.Dto.EmployeeProfile;
import com.mii.client.Models.AuthRequest;
import com.mii.client.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author jakab
 */
@Service
public class MessageService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    EmployeeService employeeService;

    @Value("${api.url}/api/message")
    private String url;
    
    public void sentRequest() {
        EmployeeProfile profile = employeeService.getByUsername(new AuthRequest());
        Employee employee = employeeService.getEmployee(profile.getEmployeeId());
        Integer id = employee.getManagerId();
        HttpEntity entity = new HttpEntity(id, RequestFormat.createHeaders());
        ResponseEntity<Boolean> res = restTemplate.exchange(url+"/request", HttpMethod.POST, 
                entity, Boolean.class);

    }
    
}
