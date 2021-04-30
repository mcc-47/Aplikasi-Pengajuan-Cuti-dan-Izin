/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.services;

import com.mii.server.dto.EmployeeDto;
import com.mii.server.entities.Employee;
import com.mii.server.entities.User;
import com.mii.server.repositories.EmployeeRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author jakab
 */
@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository employeeRepository;
    
    @Autowired
    UserService userService;
    
    public List<EmployeeDto> listAll(){
        List<EmployeeDto> emps = new ArrayList<>();
        for (Employee employee : employeeRepository.findAll()) {
            emps.add(new EmployeeDto(
                    employee.getEmployeeId(),
                    employee.getEmployeeName(), 
                    employee.getGender(), 
                    employee.getReligion(), 
                    employee.getEmail(), 
                    employee.getJobTitle(), 
                    employee.getTotalLeave(), 
                    employee.getEntryDate(), 
                    employee.getDischargeDate(),
                    employee.getManagerId().getEmployeeId()));
        }
        System.out.println("list all employee");
        return emps;
    }
    
    public EmployeeDto getOneById(Integer id){
        Employee emp = employeeRepository.findById(id).get();
        System.out.println("get one employee");
        return new EmployeeDto(
                emp.getEmployeeId(), 
                emp.getEmployeeName(), 
                emp.getGender(), 
                emp.getReligion(), 
                emp.getEmail(), 
                emp.getJobTitle(), 
                emp.getTotalLeave(), 
                emp.getEntryDate(), 
                emp.getDischargeDate(), 
                emp.getManagerId().getEmployeeId());
    }
    
    public EmployeeDto getOneByUsername(String username){
        User user = userService.loadUserByUsername(username);
        Employee emp = employeeRepository.findById(user.getUserId()).get();
        System.out.println("get one employee by username");
        return new EmployeeDto(
                emp.getEmployeeId(), 
                emp.getEmployeeName(), 
                emp.getGender(), 
                emp.getReligion(), 
                emp.getEmail(), 
                emp.getJobTitle(), 
                emp.getTotalLeave(), 
                emp.getEntryDate(), 
                emp.getDischargeDate(), 
                emp.getManagerId().getEmployeeId());
    }
    
    public EmployeeDto create(EmployeeDto emp){
        Employee newEmp = new Employee(
                emp.getEmployeeName(), 
                emp.getGender(), 
                emp.getReligion(), 
                emp.getEmail(), 
                emp.getJobTitle(), 
                emp.getTotalLeave(), 
                emp.getEntryDate(), 
                emp.getDischargeDate(), 
                new Employee(emp.getEmployeeId()));
        employeeRepository.save(newEmp);
        System.out.println("save new employee");
        return emp;
    }
    
    public  EmployeeDto update(Integer id, EmployeeDto emp){
        Employee updEmp = employeeRepository.findById(id).get();
        updEmp.setEmployeeName(emp.getEmployeeName());
        updEmp.setGender(emp.getGender());
        updEmp.setReligion(emp.getReligion());
        updEmp.setEmail(emp.getEmail());
        updEmp.setJobTitle(emp.getJobTitle());
        updEmp.setTotalLeave(emp.getTotalLeave());
        updEmp.setEntryDate(emp.getEntryDate());
        updEmp.setDischargeDate(emp.getDischargeDate());
        updEmp.setManagerId(new Employee(emp.getManagerId()));
        employeeRepository.save(updEmp);
        System.out.println("update exist employee");
        return emp;
    }
    
    public void delete(Integer id){
        employeeRepository.deleteById(id);
        System.out.println("delete that employee");
    }
}
