/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jakab
 */
@Entity
@Table(name = "leave_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LeaveType.findAll", query = "SELECT l FROM LeaveType l")
    , @NamedQuery(name = "LeaveType.findByLeaveId", query = "SELECT l FROM LeaveType l WHERE l.leaveId = :leaveId")
    , @NamedQuery(name = "LeaveType.findByLevaeName", query = "SELECT l FROM LeaveType l WHERE l.levaeName = :levaeName")
    , @NamedQuery(name = "LeaveType.findByLeaveDuration", query = "SELECT l FROM LeaveType l WHERE l.leaveDuration = :leaveDuration")})
public class LeaveType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "leave_id")
    private Integer leaveId;
    @Basic(optional = false)
    @Column(name = "levae_name")
    private String levaeName;
    @Basic(optional = false)
    @Column(name = "leave_duration")
    private int leaveDuration;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leaveId")
    private Collection<Request> requestCollection;

    public LeaveType() {
    }

    public LeaveType(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public LeaveType(Integer leaveId, String levaeName, int leaveDuration) {
        this.leaveId = leaveId;
        this.levaeName = levaeName;
        this.leaveDuration = leaveDuration;
    }

    public Integer getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(Integer leaveId) {
        this.leaveId = leaveId;
    }

    public String getLevaeName() {
        return levaeName;
    }

    public void setLevaeName(String levaeName) {
        this.levaeName = levaeName;
    }

    public int getLeaveDuration() {
        return leaveDuration;
    }

    public void setLeaveDuration(int leaveDuration) {
        this.leaveDuration = leaveDuration;
    }

    @XmlTransient
    public Collection<Request> getRequestCollection() {
        return requestCollection;
    }

    public void setRequestCollection(Collection<Request> requestCollection) {
        this.requestCollection = requestCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (leaveId != null ? leaveId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LeaveType)) {
            return false;
        }
        LeaveType other = (LeaveType) object;
        if ((this.leaveId == null && other.leaveId != null) || (this.leaveId != null && !this.leaveId.equals(other.leaveId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mii.server.entities.LeaveType[ leaveId=" + leaveId + " ]";
    }
    
}
