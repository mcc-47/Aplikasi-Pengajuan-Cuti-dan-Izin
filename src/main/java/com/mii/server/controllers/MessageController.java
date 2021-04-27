/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.controllers;

import com.mii.server.services.MessageService;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author jakab
 */
@Controller
@RequestMapping("/api/message")
public class MessageController {
    
    @Autowired
    MessageService messageService;
    
    @PostMapping("/request")
    public ResponseEntity<?> leaveRequest2Manager (Integer managerId) throws MessagingException{
        if (managerId==null) {
            return new ResponseEntity<>("manager not found", HttpStatus.NOT_ACCEPTABLE);
        }
        messageService.sentRequest(managerId);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/result")
    public ResponseEntity<?> requestResult2Employee (Integer employeeId) throws MessagingException{
        if (employeeId==null) {
            return new ResponseEntity<>("insert employee id first", HttpStatus.NOT_ACCEPTABLE);
        }
        messageService.sentResult(employeeId);
        return new ResponseEntity<>(true, HttpStatus.ACCEPTED);
    }
    
}
