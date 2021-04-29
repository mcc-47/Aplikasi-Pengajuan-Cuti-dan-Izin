/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Controller;

import com.mii.client.Models.Holiday;
import com.mii.client.Service.HolidayService;
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
 * @author Fadel
 */
@Controller
@RequestMapping("/holiday")
public class HolidayController {
    
    @Autowired
    HolidayService holidayService;
    
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("holiday", holidayService.getAll());
        System.out.println("holiday page");
        return "adminhr/holiday";
    }
    
    @GetMapping("/get-all")
    public @ResponseBody
    List<Holiday> getAllProcess() {
        return holidayService.getAll();
    }

    @GetMapping("/{id}")
    public @ResponseBody
    Holiday getById(@PathVariable("id") Integer id) {
        return holidayService.getById(id);
    }

    @PostMapping
    public @ResponseBody
    Holiday create(@RequestBody Holiday holiday) {
        return holidayService.create(holiday);
    }

    @PutMapping("/{id}")
    public @ResponseBody
    Holiday update(@PathVariable("id") Integer id, @RequestBody Holiday holiday) {
        return holidayService.updateById(id, holiday);
    }

    @DeleteMapping("/{id}")
    public @ResponseBody Integer delete(@PathVariable("id") Integer id) {
        holidayService.delete(id);
        return id;
    }
    
}
