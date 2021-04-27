/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.services;

import com.mii.server.entities.Holiday;
import com.mii.server.repositories.HolidayRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jakab
 */
@Service
public class HolidayService {
    
    @Autowired
    HolidayRepository holidayRepository;
    
    public List<Holiday> listAll(){
        return holidayRepository.findAll();
    }
    
    public Holiday getOne(Integer id){
        return holidayRepository.findById(id).get();
    }
    
    public Holiday create(Holiday holiday){
        return holidayRepository.save(holiday);
    }
    
    public Holiday update(Integer id, Holiday holiday){
        Holiday oldHoliday = holidayRepository.getOne(id);
        oldHoliday.setName(holiday.getName());
        oldHoliday.setHolidayDate(holiday.getHolidayDate());
        return holidayRepository.save(oldHoliday);
    }
    
    public void delete(Integer id){
        holidayRepository.deleteById(id);
    }
    
}
