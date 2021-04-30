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
public class RequesterList {
    
    private Integer reqId;
    private String employeeName;
    private String leaveName;
    private Date startDate;
    private Integer duration;
    private String reasons;
    private String statusName;

    public RequesterList() {
    }

    public RequesterList(Integer reqId, String employeeName, String leaveName, Date startDate, Integer duration, String reasons, String statusName) {
        this.reqId = reqId;
        this.employeeName = employeeName;
        this.leaveName = leaveName;
        this.startDate = startDate;
        this.duration = duration;
        this.reasons = reasons;
        this.statusName = statusName;
    }
    
}
