/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Controller;

import com.mii.client.Models.MandatoryLeave;
import com.mii.client.Service.MandatoryLeaveService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author jakab
 */
@Controller
@RequestMapping("/mandatory-leave")
public class MandatoryLeaveController {
    
    @Autowired
    MandatoryLeaveService mandatoryLeaveService;
    
    @GetMapping
    public String getAll(Model model) {
//        model.addAttribute("mandatoryLeave", mandatoryLeaveService.getAll());
        return "adminhr/mandatory-leave";
    }
    
    @GetMapping("/get-all")
    public @ResponseBody
    List<MandatoryLeave> getAllProcess() {
        return mandatoryLeaveService.getAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    MandatoryLeave getById(@PathVariable("id") Integer id) {
        return mandatoryLeaveService.getById(id);
    }

    @PostMapping
    public @ResponseBody
    MandatoryLeave create(@RequestBody MandatoryLeave mandatoryLeave) {
        return mandatoryLeaveService.create(mandatoryLeave);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    MandatoryLeave update(@PathVariable("id") Integer id, @RequestBody MandatoryLeave mandatoryLeave) {
        return mandatoryLeaveService.updateById(id, mandatoryLeave);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody Integer delete(@PathVariable("id") Integer id) {
        mandatoryLeaveService.delete(id);
        return id;
    }
}