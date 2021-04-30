/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Dto;

import lombok.Data;

/**
 *
 * @author jakab
 */
@Data
public class ApprovalResult {
    
    private Integer reqId;
    private Integer statusId;
    private String note;

    public ApprovalResult() {
    }

    public ApprovalResult(Integer reqId, Integer statusId, String note) {
        this.reqId = reqId;
        this.statusId = statusId;
        this.note = note;
    }
    
}
