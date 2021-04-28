/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.controllers;

import com.mii.server.entities.ManagerFill;
import com.mii.server.services.ManagerFillService;
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
@RequestMapping("/api/manager-fill")
public class ManagerFillController {
    
    @Autowired
    ManagerFillService managerFillService;
    
    //    CRUD MAPING
    @GetMapping
    public @ResponseBody List<ManagerFill> getAllManagerFill(){
        return managerFillService.listAll();
    }
    
    @GetMapping("/{id}")
    public @ResponseBody ManagerFill getOneManagerFill(@PathVariable Integer id){
        return managerFillService.getOne(id);
    }
    
    @PostMapping
    public @ResponseBody ManagerFill createManagerFill(@RequestBody ManagerFill managerFill){
        return managerFillService.create(managerFill);
    }
    
    @PutMapping("/{id}")
    public @ResponseBody ManagerFill updateManagerFill(@PathVariable Integer id, @RequestBody ManagerFill managerFill){
        return managerFillService.update(id, managerFill);
    }
    
    @DeleteMapping("/{id}")
    public void deleteManagerFill(@PathVariable Integer id){
        managerFillService.delete(id);
    }
    
}
