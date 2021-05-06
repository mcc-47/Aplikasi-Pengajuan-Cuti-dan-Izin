/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jakab
 */
@Entity
@Table(name = "request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r")
    , @NamedQuery(name = "Request.findByReqId", query = "SELECT r FROM Request r WHERE r.reqId = :reqId")
    , @NamedQuery(name = "Request.findByLeaveDuration", query = "SELECT r FROM Request r WHERE r.leaveDuration = :leaveDuration")
    , @NamedQuery(name = "Request.findByStartDate", query = "SELECT r FROM Request r WHERE r.startDate = :startDate")})
public class Request implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "req_id")
    private Integer reqId;
    @Basic(optional = false)
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @Column(name = "leave_duration")
    private Integer leaveDuration;
    @Basic(optional = false)
    @Lob
    @Column(name = "reasons")
    private String reasons;
    
    @JoinColumn(name = "employee_id", referencedColumnName = "employee_id")
    @ManyToOne(optional = false)
    private Employee employeeId;
    
    @JoinColumn(name = "leave_id", referencedColumnName = "leave_id")
    @ManyToOne(optional = false)
    private LeaveType leaveId;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "request")
    @Basic(optional = true)
    private ManagerFill managerFill;

    public Request() {
    }

    public Request(Integer reqId, Date startDate, Date endDate, Integer leaveDuration, String reasons, Employee employeeId, LeaveType leaveId) {
        this.reqId = reqId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveDuration = leaveDuration;
        this.reasons = reasons;
        this.employeeId = employeeId;
        this.leaveId = leaveId;
    }

    public Request(Integer reqId) {
        this.reqId = reqId;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public Integer getLeaveDuration() {
        return leaveDuration;
    }

    public void setLeaveDuration(Integer leaveDuration) {
        this.leaveDuration = leaveDuration;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getReasons() {
        return reasons;
    }

    public void setReasons(String reasons) {
        this.reasons = reasons;
    }

    public Employee getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Employee employeeId) {
        this.employeeId = employeeId;
    }

    public LeaveType getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(LeaveType leaveId) {
        this.leaveId = leaveId;
    }

    public ManagerFill getManagerFill() {
        return managerFill;
    }

    public void setManagerFill(ManagerFill managerFill) {
        this.managerFill = managerFill;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reqId != null ? reqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.reqId == null && other.reqId != null) || (this.reqId != null && !this.reqId.equals(other.reqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mii.server.entities.Request[ reqId=" + reqId + " ]";
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
}
