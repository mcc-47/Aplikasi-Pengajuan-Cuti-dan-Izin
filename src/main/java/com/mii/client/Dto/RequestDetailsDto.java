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
public class RequestDetailsDto {
    
    private String leaveName;
    private String reasons;
    private String statusName;
    private String managerName;
    private String note;
    private Date approvalDate;

    public RequestDetailsDto(String leaveName, String reasons, String statusName, String managerName, String note, Date approvalDate) {
        this.leaveName = leaveName;
        this.reasons = reasons;
        this.statusName = statusName;
        this.managerName = managerName;
        this.note = note;
        this.approvalDate = approvalDate;
    }
    
}
