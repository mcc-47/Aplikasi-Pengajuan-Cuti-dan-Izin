/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jakab
 */
@Entity
@Table(name = "mandatory_leave")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MandatoryLeave.findAll", query = "SELECT m FROM MandatoryLeave m")
    , @NamedQuery(name = "MandatoryLeave.findById", query = "SELECT m FROM MandatoryLeave m WHERE m.id = :id")
    , @NamedQuery(name = "MandatoryLeave.findByName", query = "SELECT m FROM MandatoryLeave m WHERE m.name = :name")
    , @NamedQuery(name = "MandatoryLeave.findByStartDate", query = "SELECT m FROM MandatoryLeave m WHERE m.startDate = :startDate")
    , @NamedQuery(name = "MandatoryLeave.findByDuration", query = "SELECT m FROM MandatoryLeave m WHERE m.duration = :duration")})
public class MandatoryLeave implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @Column(name = "duration")
    private int duration;

    public MandatoryLeave() {
    }

    public MandatoryLeave(Integer id) {
        this.id = id;
    }

    public MandatoryLeave(String name, Date startDate, int duration) {
        this.name = name;
        this.startDate = startDate;
        this.duration = duration;
    }

    public MandatoryLeave(Integer id, String name, Date startDate, int duration) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MandatoryLeave)) {
            return false;
        }
        MandatoryLeave other = (MandatoryLeave) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mii.server.entities.MandatoryLeave[ id=" + id + " ]";
    }
    
}
