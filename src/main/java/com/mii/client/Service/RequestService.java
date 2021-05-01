/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Service;

import com.mii.client.Config.RequestFormat;
import com.mii.client.Dto.EmployeeProfile;
import com.mii.client.Dto.RequestLeave;
import com.mii.client.Dto.RequestList;
import com.mii.client.Models.AuthRequest;
import com.mii.client.Models.Employee;
import com.mii.client.Models.Request;
import java.util.ArrayList;
import java.util.List;
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
public class RequestService {
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    EmployeeService employeeService;
    
    @Value("${api.url}/api/request")
    private String url;
    
    public List<RequestList> getByEmpId() {
        EmployeeProfile byUname = employeeService.getByUsername(new AuthRequest());
        Employee employeeId = new Employee(byUname.getEmployeeId());
        HttpEntity entity = new HttpEntity(employeeId, RequestFormat.createHeaders());
        ResponseEntity<List<Request>> response = restTemplate
                .exchange(url+"/by-employeeId", HttpMethod.POST, entity,
                        new ParameterizedTypeReference<List<Request>>() {
                });
        
        List<RequestList> reqList = new ArrayList<>();
        for (Request req : response.getBody()) {
            reqList.add(new RequestList(
                    req.getReqId(), 
                    req.getLeaveId().getLevaeName(), 
                    req.getLeaveDuration(), 
                    req.getStartDate(), 
                    req.getReasons(), 
                    employeeService.getEmployee(req.getManagerFill().getManagerId()).getEmployeeName(), 
                    req.getManagerFill().getStatusId().getStatusName()));
        }
        return reqList;
    }
    
    public Request create(RequestLeave req) {
        EmployeeProfile byUname = employeeService.getByUsername(new AuthRequest());
        req.setEmployeeId(byUname.getEmployeeId());
        HttpEntity entity = new HttpEntity(req, RequestFormat.createHeaders());
        ResponseEntity<Request> res = restTemplate.exchange(url, HttpMethod.POST, entity, Request.class);

        return res.getBody();
    }
    
}