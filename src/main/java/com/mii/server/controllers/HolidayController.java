/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.controllers;

import com.mii.server.dto.EmployeeDto;
import com.mii.server.entities.Holiday;
import com.mii.server.services.HolidayService;
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
@RequestMapping("/api/holiday")
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class HolidayController {
    
    @Autowired
    HolidayService holidayService;
    
    @PreAuthorize("hasAuthority('ADMINHR_READ')")
    @GetMapping
    public @ResponseBody List<Holiday> getAllHoliday(){
        return holidayService.listAll();
    }
    
    @PreAuthorize("hasAuthority('ADMINHR_READ')")
    @GetMapping("/{id}")
    public @ResponseBody Holiday getOneHoliday(@PathVariable Integer id){
        return holidayService.getOne(id);
    }
    
    @PreAuthorize("hasAuthority('ADMINHR_CREATE')")
    @PostMapping
    public @ResponseBody Holiday createHoliday(@RequestBody Holiday holiday){
        return holidayService.create(holiday);
    }
    
    @PreAuthorize("hasAuthority('ADMINHR_UPDATE')")
    @PutMapping("/{id}")
    public @ResponseBody Holiday updateHoliday(@PathVariable Integer id, @RequestBody Holiday holiday){
        return holidayService.update(id, holiday);
    }
    
    @PreAuthorize("hasAuthority('ADMINHR_DELETE')")
    @DeleteMapping("/{id}")
    public void deleteHoliday(@PathVariable Integer id){
        holidayService.delete(id);
    }
    
}
