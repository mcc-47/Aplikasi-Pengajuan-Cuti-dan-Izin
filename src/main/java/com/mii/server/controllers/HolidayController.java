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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jakab
 */
@RestController
@RequestMapping("/api/holiday")
public class HolidayController {
    
    @Autowired
    HolidayService holidayService;
    
    @GetMapping
    public List<Holiday> getAllHoliday(){
        return holidayService.listAll();
    }
    
    @GetMapping("/{id}")
    public Holiday getOneHoliday(@PathVariable Integer id){
        return holidayService.getOne(id);
    }
    
    @PostMapping
    public Holiday createHoliday(@RequestBody Holiday holiday){
        return holidayService.create(holiday);
    }
    
    @PutMapping("/{id}")
    public Holiday updateHoliday(@PathVariable Integer id, @RequestBody Holiday holiday){
        return holidayService.update(id, holiday);
    }
    
    @DeleteMapping("/{id}")
    public void deleteHoliday(@PathVariable Integer id){
        holidayService.delete(id);
    }
    
}
