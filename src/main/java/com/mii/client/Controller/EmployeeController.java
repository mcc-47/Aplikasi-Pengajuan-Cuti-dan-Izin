/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Controller;

import com.mii.client.Dto.EmployeeProfile;
import com.mii.client.Models.AuthRequest;
import com.mii.client.Models.Employee;
import com.mii.client.Models.Holiday;
import com.mii.client.Service.EmployeeService;
import com.mii.client.Service.HolidayService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author MyLaptop
 */
@Controller
@RequestMapping("employee")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;
    
    @Autowired
    HolidayService holidayService;
    
    @GetMapping("/profile")
    public String getAll(Model model) {
        System.out.println("profile page");
        return "employee/profile";
    }
    
    @GetMapping("/{id}")
    public @ResponseBody
    Employee getById(@PathVariable("id") Integer id) {
        return employeeService.getEmployee(id);
    }

    @PostMapping("/by-username")
    public @ResponseBody
    EmployeeProfile getByUsername(@RequestBody AuthRequest auth) {
        return employeeService.getByUsername(auth);
    }
    
//    @PutMapping("/{id}")
//    public @ResponseBody
//    Employee update(@PathVariable("id") Integer id, @RequestBody Employee employee) {
//        return employeeService.updateById(id, employee);
//    }
    
    @GetMapping("/get-holidays")
    public @ResponseBody
    List<String> getAllProcess() {
        List<Holiday> holidays = holidayService.getAll();
        List<String> dates = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Holiday holiday : holidays) {
            dates.add(formatter.format(holiday.getHolidayDate()));
        }
        return dates;
    }
}
