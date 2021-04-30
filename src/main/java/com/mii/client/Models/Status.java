/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.client.Models;

import lombok.Data;

/**
 *
 * @author jakab
 */
@Data
public class Status {
    
    private Integer statusId;
    private String statusName;

    public Status() {
    }

    public Status(Integer statusId) {
        this.statusId = statusId;
    }

    public Status(Integer statusId, String statusName) {
        this.statusId = statusId;
        this.statusName = statusName;
    }
    
}
