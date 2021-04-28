/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.dto;

import java.util.Date;

/**
 *
 * @author jakab
 */
public class RequestDto {
    
    private Integer reqId;
    private Integer employeeId;
    private Integer leaveId;
    private String reasons;
    private Date startDate;

    public RequestDto() {
    }

    public RequestDto(Integer employeeId, Integer leaveId, String reasons, Date startDate) {
        this.employeeId = employeeId;
        this.leaveId = leaveId;
        this.reasons = reasons;
        this.startDate = startDate;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
}
