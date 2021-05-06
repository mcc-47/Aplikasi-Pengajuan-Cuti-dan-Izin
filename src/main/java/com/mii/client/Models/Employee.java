/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Models;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author jakab
 */
@Data
public class Employee {
    
    private Integer employeeId;
    private String employeeName;
    private String gender;
    private String religion;
    private String email;
    private String jobTitle;
    private String maritalStatus;
    private Integer totalLeave;
    private Date entryDate;
    private Date dischargeDate;
    private Integer managerId;

    public Employee() {
    }

    public Employee(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Employee(String employeeName, String gender, String religion, String email, String jobTitle, String maritalStatus, Integer totalLeave, Date entryDate, Date dischargeDate, Integer managerId) {
        this.employeeName = employeeName;
        this.gender = gender;
        this.religion = religion;
        this.email = email;
        this.jobTitle = jobTitle;
        this.maritalStatus = maritalStatus;
        this.totalLeave = totalLeave;
        this.entryDate = entryDate;
        this.dischargeDate = dischargeDate;
        this.managerId = managerId;
    }

    public Employee(Integer employeeId, String employeeName, String gender, String religion, String email, String jobTitle, Integer totalLeave, Date entryDate, Date dischargeDate, Integer managerId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.gender = gender;
        this.religion = religion;
        this.email = email;
        this.jobTitle = jobTitle;
        this.maritalStatus = maritalStatus;
        this.totalLeave = totalLeave;
        this.entryDate = entryDate;
        this.dischargeDate = dischargeDate;
        this.managerId = managerId;
    }
    
}
