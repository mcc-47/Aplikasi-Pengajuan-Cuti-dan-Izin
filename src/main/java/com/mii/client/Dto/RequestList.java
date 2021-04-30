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
public class RequestList {
    
    private Integer reqId;
    private String leaveName;
    private Integer leaveDuration;
    private Date startDate;
    private String reasons;
    private String manager;
    private String statusName;

    public RequestList() {
    }

    public RequestList(Integer reqId, String leaveName, Integer leaveDuration, Date startDate, String reasons, String manager, String statusName) {
        this.reqId = reqId;
        this.leaveName = leaveName;
        this.leaveDuration = leaveDuration;
        this.startDate = startDate;
        this.reasons = reasons;
        this.manager = manager;
        this.statusName = statusName;
    }
    
    
}
