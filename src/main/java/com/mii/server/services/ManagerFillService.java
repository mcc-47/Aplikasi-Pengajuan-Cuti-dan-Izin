/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.services;

import com.mii.server.dto.RequesterListDto;
import com.mii.server.entities.ManagerFill;
import com.mii.server.repositories.ManagerFillRepository;
import java.util.ArrayList;
import java.util.List;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jakab
 */
@Service
public class ManagerFillService {
    
    @Autowired
    ManagerFillRepository managerFillRepository;
    
    @Autowired
    MessageService ms;
    
//    CRUD
    public List<ManagerFill> listAll(){
        return managerFillRepository.findAll();
    }
    
    public ManagerFill getOne(Integer id){
        return managerFillRepository.findById(id).get();
    }
    
    public List<RequesterListDto> getByManagerId(Integer managerId){
        List<ManagerFill> mgrFill = managerFillRepository.findByManagerId(managerId);
        List<RequesterListDto> reqListDto = new ArrayList<>();
        for (ManagerFill managerFill : mgrFill) {
            reqListDto.add(new RequesterListDto(
                    managerFill.getReqId(), 
                    managerFill.getRequest().getEmployeeId().getEmployeeName(), 
                    managerFill.getRequest().getLeaveId().getLevaeName(), 
                    managerFill.getRequest().getStartDate(), 
                    managerFill.getRequest().getLeaveDuration(), 
                    managerFill.getRequest().getReasons(), 
                    managerFill.getStatusId().getStatusName()));
        }
        return reqListDto;
    }
    
    public ManagerFill create(ManagerFill managerFill){
        return managerFillRepository.save(managerFill);
    }
    
    public ManagerFill update(Integer id, ManagerFill managerFill) throws MessagingException{
        ManagerFill oldManagerFill = managerFillRepository.getOne(id);
        oldManagerFill.setNote(managerFill.getNote());
        oldManagerFill.setStatusId(managerFill.getStatusId());
        oldManagerFill.setApprovementDate(managerFill.getApprovementDate());
        
        if (true) {
            ms.sentResult(oldManagerFill.getRequest().getEmployeeId().getEmployeeId());
        }
        
        return managerFillRepository.save(oldManagerFill);
    }
    
    public void delete(Integer id){
        managerFillRepository.deleteById(id);
        System.out.println("is deleted?");
    }
    
}
