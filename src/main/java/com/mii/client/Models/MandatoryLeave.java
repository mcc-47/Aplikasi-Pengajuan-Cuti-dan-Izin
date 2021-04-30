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
public class MandatoryLeave {
    
    private Integer id;
    private String name;
    private Date startDate;
    private Integer duration;

    public MandatoryLeave() {
    }

    public MandatoryLeave(Integer id, String name, Date startDate, Integer duration) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.duration = duration;
    }

    public MandatoryLeave(String name, Date startDate, Integer duration) {
        this.name = name;
        this.startDate = startDate;
        this.duration = duration;
    }
    
    
}
