/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Dto;

import java.util.Date;
import lombok.Data;

/**
 *
 * @author jakab
 */
@Data
public class EmployeeProfile {
    
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
    private String managerId;

    public EmployeeProfile() {
    }

    public EmployeeProfile(String employeeName, String gender, String religion, String email, String jobTitle, String maritalStatus, Integer totalLeave, Date entryDate, String managerId) {
        this.employeeName = employeeName;
        this.gender = gender;
        this.religion = religion;
        this.email = email;
        this.jobTitle = jobTitle;
        this.maritalStatus = maritalStatus;
        this.totalLeave = totalLeave;
        this.entryDate = entryDate;
        this.managerId = managerId;
    }

    public EmployeeProfile(Integer employeeId, String employeeName, String gender, String religion, String email, String jobTitle, String maritalStatus, Integer totalLeave, Date entryDate, String managerId) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.gender = gender;
        this.religion = religion;
        this.email = email;
        this.jobTitle = jobTitle;
        this.maritalStatus = maritalStatus;
        this.totalLeave = totalLeave;
        this.entryDate = entryDate;
        this.managerId = managerId;
    }
    
}
