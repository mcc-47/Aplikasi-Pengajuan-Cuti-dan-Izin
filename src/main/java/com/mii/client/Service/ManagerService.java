/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Service;

import com.mii.client.Config.RequestFormat;
import com.mii.client.Dto.ApprovalModal;
import com.mii.client.Dto.ApprovalResult;
import com.mii.client.Dto.EmployeeProfile;
import com.mii.client.Dto.RequesterList;
import com.mii.client.Models.AuthRequest;
import com.mii.client.Models.Employee;
import com.mii.client.Models.ManagerFill;
import com.mii.client.Models.Request;
import com.mii.client.Models.Status;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
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
public class ManagerService {
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private RequestService requestService;
   
    @Value("${api.url}/api/manager")
    private String url;
    
     public List<RequesterList> getAllByMgrId() {
        EmployeeProfile byUname = employeeService.getByUsername(new AuthRequest());
        Employee employeeId = new Employee(byUname.getEmployeeId());
        HttpEntity entity = new HttpEntity(employeeId, RequestFormat.createHeaders());
        ResponseEntity<List<RequesterList>> response = restTemplate
                .exchange(url+"/by-managerId", HttpMethod.POST, entity,
                        new ParameterizedTypeReference<List<RequesterList>>() {
                });
        return response.getBody();
    }
    
    public ApprovalModal getById(Integer id) {
        ResponseEntity<Request> response = restTemplate
                .exchange("http://localhost:8091/api/request/"+id, HttpMethod.GET, 
                        new HttpEntity<>(RequestFormat.createHeaders()),
                        new ParameterizedTypeReference<Request>() {
                });
        ApprovalModal modal = new ApprovalModal(
                response.getBody().getReqId(),
                response.getBody().getEmployeeId().getEmployeeName(), 
                response.getBody().getEmployeeId().getTotalLeave(), 
                response.getBody().getStartDate(), 
                response.getBody().getEndDate(), 
                response.getBody().getLeaveDuration(), 
                response.getBody().getManagerFill().getNote(), 
                response.getBody().getManagerFill().getStatusId().getStatusName());
        return modal;
    }
    
//    public ManagerFill getById(Integer id) {
//        
//        return restTemplate.getForEntity(url + "/" + id, ManagerFill.class).getBody();
//    }

    public ManagerFill updateApproval(Integer id, ApprovalResult approval) {
        LocalDate now = LocalDate.now();
        ManagerFill manager = new ManagerFill(id, approval.getNote(), new Status(approval.getStatusId()));
        manager.setApprovementDate(Date.from(now.atStartOfDay().toInstant(ZoneOffset.UTC)));
        HttpEntity entity = new HttpEntity(manager, RequestFormat.createHeaders());
        ResponseEntity<ManagerFill> res = restTemplate.exchange(url + "/" + id, HttpMethod.PUT, entity, ManagerFill.class);
        
        return res.getBody();
    }

}

