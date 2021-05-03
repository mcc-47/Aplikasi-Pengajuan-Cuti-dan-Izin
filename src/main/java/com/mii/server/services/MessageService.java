/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.services;

import com.mii.server.entities.Employee;
import com.mii.server.repositories.EmployeeRepository;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author jakab
 */
@Service
public class MessageService {
    
    @Autowired
    JavaMailSender javaMailSender;
    
    @Autowired
    EmployeeRepository employeeRepository;
    
//    @Autowired
//    private SpringTemplateEngine templateEngine;
    
    @Value("${spring.mail.username}")
    String sender;

    public MessageService(JavaMailSender javaMailSender, EmployeeRepository employeeRepository) {
        this.javaMailSender = javaMailSender;
        this.employeeRepository = employeeRepository;
    }
    
    public void sentRequest(Integer employeeId) throws MessagingException{
        //Finding employee //Mime making email
        Employee manager = employeeRepository.findById(employeeId).get();
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        
//        String html = templateEngine.process("file.html",true);
        
        //From (App) sender
        helper.setFrom(sender);
        //To (person) receiver
        helper.setTo(manager.getEmail());
       //Subject maker
         helper.setSubject("Leave Request");
        //Mail body
        message.setText("You got leave request", "UTF-8", "html");
        //Sending email
        javaMailSender.send(message);
    }
    
    public void sentResult(Integer employeeId) throws MessagingException{
        //Finding employee
        Employee employee = employeeRepository.findById(employeeId).get();
        //Mime making email
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        //From (App) sender
        helper.setFrom(sender);
        //To (person) receiver
        helper.setTo(employee.getEmail());
        //Subject maker
        helper.setSubject("Approval Result");
        //Mail body
        message.setText("Here you requested answer", "UTF-8", "html");
        //Sending email
        javaMailSender.send(message);
    }
    
}
