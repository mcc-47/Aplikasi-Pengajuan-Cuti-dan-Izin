/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Service;

import com.mii.client.Config.RequestFormat;
import com.mii.client.Models.AuthRequest;
import com.mii.client.Models.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author MyLaptop
 */
@Service
public class EmployeeService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url}/api/employee")
    private String url;
    
    public Employee getById(Integer id) {
        return restTemplate.getForEntity(url + "/" + id, Employee.class).getBody();
    }
    
    public Employee create(AuthRequest username) {
        HttpEntity entity = new HttpEntity(username, RequestFormat.createHeaders());
        ResponseEntity<Employee> res = restTemplate.exchange(url+"/by-username",
                HttpMethod.POST, entity, Employee.class);

        return res.getBody();
    }
    
}
