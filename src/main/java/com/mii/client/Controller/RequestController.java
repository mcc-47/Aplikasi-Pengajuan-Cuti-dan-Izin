/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Controller;

import com.mii.client.Dto.RequestDetailsDto;
import com.mii.client.Dto.RequestLeave;
import com.mii.client.Dto.RequestList;
import com.mii.client.Models.Request;
import com.mii.client.Service.RequestService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jakab
 */
@Controller
@RequestMapping("request")
public class RequestController {
    
    @Autowired
    RequestService requestService;
    
    @GetMapping
    public String getAll(Model model) {
        return "employee/employee-req";
    }
    
    @GetMapping("/by-empId")
    public @ResponseBody
    List<RequestList> getAllProcess() {
        return requestService.getByEmpId();
    }
    
    @GetMapping("/{id}")
    public @ResponseBody
    RequestDetailsDto getById(@PathVariable("id") Integer id) {
        return requestService.getById(id);
    }
    
    @PostMapping
    public @ResponseBody
    Request create(@RequestBody RequestLeave req) {
        return requestService.create(req);
    }
    
}
