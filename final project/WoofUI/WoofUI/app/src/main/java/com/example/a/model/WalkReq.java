package com.example.a.model;

import java.util.Date;

/**
 * Created by A on 3/10/2018.
 */

public class WalkReq {
    private Integer walkReqId;

    private Date walkReqDate;

    private WalkInfo reqId;

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
