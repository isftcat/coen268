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
@Table(name = "Mate_req")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Matereq.findAll", query = "SELECT m FROM Matereq m")
    , @NamedQuery(name = "Matereq.findByMateReqId", query = "SELECT m FROM Matereq m WHERE m.mateReqId = :mateReqId")
    , @NamedQuery(name = "Matereq.findByMateReqDate", query = "SELECT m FROM Matereq m WHERE m.mateReqDate = :mateReqDate")})
public class Matereq implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mate_req_id")
    private Integer mateReqId;
    @Column(name = "mate_req_date")
    @Temporal(TemporalType.DATE)
    private Date mateReqDate;
    @JoinColumn(name = "dog_id", referencedColumnName = "dog_id")
    @ManyToOne
    private DogDetails dogId;
    @JoinColumn(name = "req_id", referencedColumnName = "mate_info_id")
    @ManyToOne
    private Mateinfo reqId;

    public Matereq() {
    }

    public Matereq(Integer mateReqId) {
        this.mateReqId = mateReqId;
    }

    public Integer getMateReqId() {
        return mateReqId;
    }

    public void setMateReqId(Integer mateReqId) {
        this.mateReqId = mateReqId;
    }

    public Date getMateReqDate() {
        return mateReqDate;
    }

    public void setMateReqDate(Date mateReqDate) {
        this.mateReqDate = mateReqDate;
    }

    public DogDetails getDogId() {
        return dogId;
    }

    public void setDogId(DogDetails dogId) {
        this.dogId = dogId;
    }

    public Mateinfo getReqId() {
        return reqId;
    }

    public void setReqId(Mateinfo reqId) {
        this.reqId = reqId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mateReqId != null ? mateReqId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Matereq)) {
            return false;
        }
        Matereq other = (Matereq) object;
        if ((this.mateReqId == null && other.mateReqId != null) || (this.mateReqId != null && !this.mateReqId.equals(other.mateReqId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Matereq[ mateReqId=" + mateReqId + " ]";
    }
    
}
