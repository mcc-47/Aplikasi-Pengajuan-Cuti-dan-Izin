/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.controllers;

import com.mii.server.dto.RequestDto;
import com.mii.server.entities.Request;
import com.mii.server.services.RequestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jakab
 */
@RestController
@RequestMapping("/api/request")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class RequestController {
    
    @Autowired
    RequestService requestService;
    
//    CRUD MAPING
    @PreAuthorize("hasAnyAuthority('ADMINHR_READ','MANAGER_READ')")
    @GetMapping
    public @ResponseBody List<Request> getAllRequest(){
        return requestService.listAll();
    }
    
    @PreAuthorize("hasAnyAuthority('EMPLOYEE_READ','MANAGER_READ')")
    @GetMapping("/{id}")
    public @ResponseBody Request getOneRequest(@PathVariable Integer id){
        return requestService.getOne(id);
    }
    
    @PreAuthorize("hasAuthority('EMPLOYEE_CREATE')")
    @PostMapping
    public @ResponseBody Request createRequest(@RequestBody RequestDto request){
        return requestService.create(request);
    }
    
    @PreAuthorize("hasAuthority('MANAGER_UPDATE')")
    @PutMapping("/{id}")
    public @ResponseBody Request updateRequest(@PathVariable Integer id, @RequestBody RequestDto request){
        return requestService.update(id, request);
    }
    
    @PreAuthorize("hasAuthority('EMPLOYEE_DELETE')")
    @DeleteMapping("/{id}")
    public void deleteRequest(@PathVariable Integer id){
        requestService.delete(id);
    }
    
}
