/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.controllers;

import com.mii.server.entities.MandatoryLeave;
import com.mii.server.services.MandatoryLeaveService;
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
@RequestMapping("/api/mandatory-leave")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MandatoryLeaveController {
    
    @Autowired
    MandatoryLeaveService mandatoryLeaveService;
    
    @PreAuthorize("hasAuthority('ADMINHR_READ')")
    @GetMapping
    public @ResponseBody List<MandatoryLeave> getAllMandatoryLeave(){
        return mandatoryLeaveService.listAll();
    }
    
    @PreAuthorize("hasAuthority('ADMINHR_READ')")
    @GetMapping("/{id}")
    public @ResponseBody MandatoryLeave getOneMandatoryLeave(@PathVariable Integer id){
        return mandatoryLeaveService.getOne(id);
    }
    
    @PreAuthorize("hasAuthority('ADMINHR_CREATE')")
    @PostMapping
    public @ResponseBody MandatoryLeave createMandatoryLeave(@RequestBody MandatoryLeave mandatoryLeave){
        return mandatoryLeaveService.create(mandatoryLeave);
    }
    
    @PreAuthorize("hasAuthority('ADMINHR_UPDATE')")
    @PutMapping("/{id}")
    public @ResponseBody MandatoryLeave updateMandatoryLeave(@PathVariable Integer id, @RequestBody MandatoryLeave mandatoryLeave){
        return mandatoryLeaveService.update(id, mandatoryLeave);
    }
    
    @PreAuthorize("hasAuthority('ADMINHR_DELETE')")
    @DeleteMapping("/{id}")
    public void deleteMandatoryLeave(@PathVariable Integer id){
        mandatoryLeaveService.delete(id);
    }
    
}
