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
public class ApprovalModal {
    
    private Integer reqId;
    private String employeeName;
    private Integer totalLeave;
    private Date startDate;
    private Date endDate;
    private Integer leaveDuration;
    private String note;
    private String statusName;

    public ApprovalModal() {
    }

    public ApprovalModal(Integer reqId, String employeeName, Integer totalLeave, Date startDate, Date endDate, Integer leaveDuration, String note, String statusName) {
        this.reqId = reqId;
        this.employeeName = employeeName;
        this.totalLeave = totalLeave;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveDuration = leaveDuration;
        this.note = note;
        this.statusName = statusName;
    }
    
}
