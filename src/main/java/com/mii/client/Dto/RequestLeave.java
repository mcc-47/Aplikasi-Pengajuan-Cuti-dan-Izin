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
public class RequestLeave {
    
    private Integer reqId;
    private Integer employeeId;
    private Integer leaveId;
    private String reasons;
    private Date startDate;

    public RequestLeave() {
    }

    public RequestLeave(Integer leaveId, String reasons, Date startDate) {
        this.leaveId = leaveId;
        this.reasons = reasons;
        this.startDate = startDate;
    }

    public RequestLeave(Integer employeeId, Integer leaveId, String reasons, Date startDate) {
        this.employeeId = employeeId;
        this.leaveId = leaveId;
        this.reasons = reasons;
        this.startDate = startDate;
    }
    
}
