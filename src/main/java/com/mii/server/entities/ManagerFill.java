/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mii.server.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "manager_fill")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ManagerFill.findAll", query = "SELECT m FROM ManagerFill m")
    , @NamedQuery(name = "ManagerFill.findByReqId", query = "SELECT m FROM ManagerFill m WHERE m.reqId = :reqId")
    , @NamedQuery(name = "ManagerFill.findByManagerId", query = "SELECT m FROM ManagerFill m WHERE m.managerId = :managerId")
    , @NamedQuery(name = "ManagerFill.findByApprovementDate", query = "SELECT m FROM ManagerFill m WHERE m.approvementDate = :approvementDate")})
public class ManagerFill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "req_id")
    private Integer reqId;
    @Basic(optional = true)
    @Lob
    @Column(name = "note")
    private String note;
    @Basic(optional = false)
    @Column(name = "manager_id")
    private int managerId;
    @Basic(optional = true)
    @Column(name = "approvement_date")
    @Temporal(TemporalType.DATE)
    private Date approvementDate;
    
    @JoinColumn(name = "status_id", referencedColumnName = "status_id")
    @ManyToOne(optional = false)
    private Status statusId;
    
    @JoinColumn(name = "req_id", referencedColumnName = "req_id", insertable = false, updatable = false)
    @OneToOne(optional = true)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Request request;

    public ManagerFill() {
    }

    public ManagerFill(Integer reqId) {
        this.reqId = reqId;
    }

    public ManagerFill(Integer reqId, int managerId, Status statusId) {
        this.reqId = reqId;
        this.managerId = managerId;
        this.statusId = statusId;
    }

    public ManagerFill(Integer reqId, String note, int managerId, Status statusId) {
        this.reqId = reqId;
        this.note = note;
        this.managerId = managerId;
        this.statusId = statusId;
    }

    public Integer getReqId() {
        return reqId;
    }

    public void setReqId(Integer reqId) {
        this.reqId = reqId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public Date getApprovementDate() {
        return approvementDate;
    }

    public void setApprovementDate(Date approvementDate) {
        this.approvementDate = approvementDate;
    }

    public Status getStatusId() {
        return statusId;
    }

    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
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
        if (!(object instanceof ManagerFill)) {
            return false;
        }
        ManagerFill other = (ManagerFill) object;
        if ((this.reqId == null && other.reqId != null) || (this.reqId != null && !this.reqId.equals(other.reqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mii.server.entities.ManagerFill[ reqId=" + reqId + " ]";
    }
    
}
