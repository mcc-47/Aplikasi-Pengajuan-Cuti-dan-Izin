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
public class RequesterListDto {
    
    private Integer reqId;
    private String employeeName;
    private String leaveName;
    private Date startDate;
    private Integer duration;
    private String reasons;
    private String statusName;

    public RequesterListDto() {
    }

    public RequesterListDto(Integer reqId, String employeeName, String leaveName, Date startDate, Integer duration, String reasons, String statusName) {
        this.reqId = reqId;
        this.employeeName = employeeName;
        this.leaveName = leaveName;
        this.startDate = startDate;
        this.duration = duration;
        this.reasons = reasons;
        this.statusName = statusName;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getLeaveName() {
        return leaveName;
    }

    public void setLeaveName(String leaveName) {
        this.leaveName = leaveName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
    
}
