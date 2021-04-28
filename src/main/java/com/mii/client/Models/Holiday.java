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
 * @author Fadel
 */
@Data
public class Holiday {
    
    private Integer holidayId;
    private String holidayName;
    private Date holidayDate;

    public Holiday(Integer holidayId, String holidayName, Date holidayDate) {
        this.holidayId = holidayId;
        this.holidayName = holidayName;
        this.holidayDate = holidayDate;
    }

    public Holiday() {
    }
    
}
