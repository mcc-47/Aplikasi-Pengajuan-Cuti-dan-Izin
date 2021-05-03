/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Controller;

import com.mii.client.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jakab
 */
@Controller
@RequestMapping("message")
public class MessageController {
    
    @Autowired
    MessageService messageService;
    
    @GetMapping("request")
    public @ResponseBody String mailRequest(){
        messageService.sentRequest();
        return "sent request";
    }
    
}
