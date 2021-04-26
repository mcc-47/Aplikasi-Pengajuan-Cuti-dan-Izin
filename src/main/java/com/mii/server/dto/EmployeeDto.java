/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.dto;

import com.mii.server.entities.Employee;
import java.util.Date;

/**
 *
 * @author jakab
 */
public class EmployeeDto {
    
    private Integer employeeId;
    private String employeeName;
    private String gender;
    private String religion;
    private String email;
    private String jobTitle;
    private Integer totalLeave;
    private Date entryDate;
    private Date dischargeDate;
    private Integer managerId;

    public EmployeeDto() {
    }

    public EmployeeDto(
            Integer employeeId, 
            String employeeName, 
            String gender, 
            String religion, 
            String email, 
            String jobTitle, 
            Integer totalLeave, 
            Date entryDate, 
            Date dischargeDate, 
            Integer managerId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.gender = gender;
        this.religion = religion;
        this.email = email;
        this.jobTitle = jobTitle;
        this.totalLeave = totalLeave;
        this.entryDate = entryDate;
        this.dischargeDate = dischargeDate;
        this.managerId = managerId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Integer getTotalLeave() {
        return totalLeave;
    }

    public void setTotalLeave(Integer totalLeave) {
        this.totalLeave = totalLeave;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }
    
    
}
