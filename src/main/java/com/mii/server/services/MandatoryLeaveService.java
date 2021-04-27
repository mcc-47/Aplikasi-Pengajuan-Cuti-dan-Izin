/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.services;

import com.mii.server.entities.MandatoryLeave;
import com.mii.server.repositories.MandatoryLeaveRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jakab
 */
@Service
public class MandatoryLeaveService {
    
    @Autowired
    MandatoryLeaveRepository mandatoryLeaveRepository;
    
    public List<MandatoryLeave> listAll(){
        return mandatoryLeaveRepository.findAll();
    }
    
    public MandatoryLeave getOne(Integer id){
        return mandatoryLeaveRepository.findById(id).get();
    }
    
    public MandatoryLeave create(MandatoryLeave mandatoryLeave){
        return mandatoryLeaveRepository.save(mandatoryLeave);
    }
    
    public MandatoryLeave update(Integer id, MandatoryLeave mandatoryLeave){
        MandatoryLeave oldMandatoryLeave = mandatoryLeaveRepository.getOne(id);
        oldMandatoryLeave.setName(mandatoryLeave.getName());
        oldMandatoryLeave.setStartDate(mandatoryLeave.getStartDate());
        oldMandatoryLeave.setDuration(mandatoryLeave.getDuration());
        return mandatoryLeaveRepository.save(oldMandatoryLeave);
    }
    
    public void delete(Integer id){
        mandatoryLeaveRepository.deleteById(id);
    }
    
}
