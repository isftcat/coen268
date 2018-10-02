/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author saishree
 */
@Entity
@Table(name = "walk_req")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WalkReq.findAll", query = "SELECT w FROM WalkReq w")
    , @NamedQuery(name = "WalkReq.findByWalkReqId", query = "SELECT w FROM WalkReq w WHERE w.walkReqId = :walkReqId")
    , @NamedQuery(name = "WalkReq.findByWalkReqDate", query = "SELECT w FROM WalkReq w WHERE w.walkReqDate = :walkReqDate")})
public class WalkReq implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "walk_req_id")
    private Integer walkReqId;
    @Column(name = "walk_req_date")
    @Temporal(TemporalType.DATE)
    private Date walkReqDate;
    @JoinColumn(name = "req_id", referencedColumnName = "walk_info_id")
    @ManyToOne
    private WalkInfo reqId;
    @JoinColumn(name = "walker_id", referencedColumnName = "owner_id")
    @ManyToOne
    private OwnerDetails walkerId;

    public WalkReq() {
    }

    public WalkReq(Integer walkReqId) {
        this.walkReqId = walkReqId;
    }

    public Integer getWalkReqId() {
        return walkReqId;
    }

    public void setWalkReqId(Integer walkReqId) {
        this.walkReqId = walkReqId;
    }

    public Date getWalkReqDate() {
        return walkReqDate;
    }

    public void setWalkReqDate(Date walkReqDate) {
        this.walkReqDate = walkReqDate;
    }

    public WalkInfo getReqId() {
        return reqId;
    }

    public void setReqId(WalkInfo reqId) {
        this.reqId = reqId;
    }

    public OwnerDetails getWalkerId() {
        return walkerId;
    }

    public void setWalkerId(OwnerDetails walkerId) {
        this.walkerId = walkerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (walkReqId != null ? walkReqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WalkReq)) {
            return false;
        }
        WalkReq other = (WalkReq) object;
        if ((this.walkReqId == null && other.walkReqId != null) || (this.walkReqId != null && !this.walkReqId.equals(other.walkReqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.WalkReq[ walkReqId=" + walkReqId + " ]";
    }
    
}
