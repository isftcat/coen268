/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author saishree
 */
@Entity
@Table(name = "walk_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "WalkInfo.findAll", query = "SELECT w FROM WalkInfo w")
    , @NamedQuery(name = "WalkInfo.findByWalkInfoId", query = "SELECT w FROM WalkInfo w WHERE w.walkInfoId = :walkInfoId")
    , @NamedQuery(name = "WalkInfo.findByWalkInfoDate", query = "SELECT w FROM WalkInfo w WHERE w.walkInfoDate = :walkInfoDate")})
    
public class WalkInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "walk_info_id")
    private Integer walkInfoId;
    @Column(name = "walk_info_date")
    @Temporal(TemporalType.DATE)
    private Date walkInfoDate;
    @Column(name = "from_time")
    @Temporal(TemporalType.TIME)
    private Date fromTime;
    @Column(name = "to_time")
    @Temporal(TemporalType.TIME)
    private Date toTime;
    @JoinColumn(name = "dog_id", referencedColumnName = "dog_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private DogDetails dogId;   
    @JoinColumn(name = "walker_id", referencedColumnName = "owner_id")
    @ManyToOne
    private OwnerDetails walkerId;
    @OneToMany(mappedBy = "reqId")
    private Collection<WalkReq> walkReqCollection;

    public WalkInfo() {
    }

    public WalkInfo(Integer walkInfoId) {
        this.walkInfoId = walkInfoId;
    }

    public Integer getWalkInfoId() {
        return walkInfoId;
    }

    public void setWalkInfoId(Integer walkInfoId) {
        this.walkInfoId = walkInfoId;
    }

    public Date getWalkInfoDate() {
        return walkInfoDate;
    }

    public void setWalkInfoDate(Date walkInfoDate) {
        this.walkInfoDate = walkInfoDate;
    }

    public Date getFromTime() {
        return fromTime;
    }

    public void setFromTime(Date walkTime) {
        this.fromTime = fromTime;
    }
    
    public Date getToTime() {
        return toTime;
    }

    public void setToTime(Date walkTime) {
        this.toTime = toTime;
    }

    public DogDetails getDogId() {
        return dogId;
    }

    public void setDogId(DogDetails dogId) {
        this.dogId = dogId;
    }

    public OwnerDetails getWalkerId() {
        return walkerId;
    }

    public void setWalkerId(OwnerDetails walkerId) {
        this.walkerId = walkerId;
    }

    @XmlTransient
    public Collection<WalkReq> getWalkReqCollection() {
        return walkReqCollection;
    }

    public void setWalkReqCollection(Collection<WalkReq> walkReqCollection) {
        this.walkReqCollection = walkReqCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (walkInfoId != null ? walkInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof WalkInfo)) {
            return false;
        }
        WalkInfo other = (WalkInfo) object;
        if ((this.walkInfoId == null && other.walkInfoId != null) || (this.walkInfoId != null && !this.walkInfoId.equals(other.walkInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.WalkInfo[ walkInfoId=" + walkInfoId + " ]";
    }
    
}
