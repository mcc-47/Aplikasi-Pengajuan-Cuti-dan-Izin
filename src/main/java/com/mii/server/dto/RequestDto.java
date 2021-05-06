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
    private Integer leaveDuration;
    private String reasons;
    private Date startDate;
    private Date endDate;

    public RequestDto() {
    }

    public RequestDto(Integer employeeId, Integer leaveId, Integer leaveDuration, String reasons, Date startDate, Date endDate) {
        this.employeeId = employeeId;
        this.leaveId = leaveId;
        this.leaveDuration = leaveDuration;
        this.reasons = reasons;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public Integer getLeaveDuration() {
        return leaveDuration;
    }

    public void setLeaveDuration(Integer leaveDuration) {
        this.leaveDuration = leaveDuration;
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

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
}
