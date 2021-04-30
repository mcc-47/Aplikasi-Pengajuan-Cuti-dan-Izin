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
public class ManagerFill {
    
    private Integer reqId;
    private String note;
    private int managerId;
    private Date approvementDate;
    
    private Status statusId;
    private Request request;

    public ManagerFill() {
    }

    public ManagerFill(Integer reqId, String note, Status statusId) {
        this.reqId = reqId;
        this.note = note;
        this.statusId = statusId;
    }

    public ManagerFill(Integer reqId, String note, int managerId, Date approvementDate, Status statusId, Request request) {
        this.reqId = reqId;
        this.note = note;
        this.managerId = managerId;
        this.approvementDate = approvementDate;
        this.statusId = statusId;
        this.request = request;
    }
    
}
