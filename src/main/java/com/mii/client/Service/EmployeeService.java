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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    
    public Employee getEmployee(Integer id) {
        ResponseEntity<Employee> response = restTemplate
                .exchange(url + "/" + id, HttpMethod.GET, new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<Employee>() {
                });
        return response.getBody();
    }
    
    public EmployeeProfile getByUsername(AuthRequest auth) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        auth.setUsername(authentication.getName());
        HttpEntity entity = new HttpEntity(auth, RequestFormat.createHeaders());
        ResponseEntity<Employee> res = restTemplate.exchange(url+"/by-username",
                HttpMethod.POST, entity, Employee.class);

        Employee emp = res.getBody();
        Employee mgr;
        mgr = getEmployee(emp.getManagerId());
        EmployeeProfile profile = new EmployeeProfile(
                emp.getEmployeeId(),
                emp.getEmployeeName(), 
                emp.getGender(), 
                emp.getReligion(), 
                emp.getEmail(), 
                emp.getJobTitle(), 
                emp.getMaritalStatus(), 
                emp.getTotalLeave(), emp.getEntryDate(), 
                mgr.getEmployeeName());
        return profile;
    }
    
//    public Employee updateById(Integer id, Employee employee) {
//        HttpEntity entity = new HttpEntity(employee, RequestFormat.createHeaders());
//        ResponseEntity<Employee> res = restTemplate.exchange(url + "/" + id, HttpMethod.PUT, entity, Employee.class);
//        
//        return res.getBody();
//    }
}
