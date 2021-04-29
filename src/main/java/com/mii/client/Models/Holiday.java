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
    
    private Integer id;
    private String name;
    private Date holidayDate;

    public Holiday(Integer id, String name, Date holidayDate) {
        this.id = id;
        this.name = name;
        this.holidayDate = holidayDate;
    }

    public Holiday(String name, Date holidayDate) {
        this.name = name;
        this.holidayDate = holidayDate;
    }

    public Holiday() {
    }
    
}
