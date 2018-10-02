package com.example.a.model;

import java.util.Date;

/**
 * Created by A on 3/7/2018.
 */

public class DogDetails {

    private Integer id;

    private String name;

    private Date dob;


    private String pic;

    private OwnerDetails ownerId;

//    private Collection<WalkInfo> walkInfoCollection;
//
//    private Collection<MateInfo> mateInfoCollection;
//
//    private Collection<DogPics> dogPicsCollection;
//
//    private Collection<MateReq> mateReqCollection;
//
    public DogDetails() {
    }

    public DogDetails(Integer id) {
        this.id = id;
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

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public OwnerDetails getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(OwnerDetails ownerId) {
        this.ownerId = ownerId;
    }
//
//    @XmlTransient
//    public Collection<WalkInfo> getWalkInfoCollection() {
//        return walkInfoCollection;
//    }
//
//    public void setWalkInfoCollection(Collection<WalkInfo> walkInfoCollection) {
//        this.walkInfoCollection = walkInfoCollection;
//    }
//
//    @XmlTransient
//    public Collection<MateInfo> getMateInfoCollection() {
//        return mateInfoCollection;
//    }
//
//    public void setMateInfoCollection(Collection<MateInfo> mateInfoCollection) {
//        this.mateInfoCollection = mateInfoCollection;
//    }
//
//    @XmlTransient
//    public Collection<DogPics> getDogPicsCollection() {
//        return dogPicsCollection;
//    }
//
//    public void setDogPicsCollection(Collection<DogPics> dogPicsCollection) {
//        this.dogPicsCollection = dogPicsCollection;
//    }
//
//    @XmlTransient
//    public Collection<MateReq> getMateReqCollection() {
//        return mateReqCollection;
//    }
//
//    public void setMateReqCollection(Collection<MateReq> mateReqCollection) {
//        this.mateReqCollection = mateReqCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DogDetails)) {
            return false;
        }
        DogDetails other = (DogDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "webwoof.DogDetails[ id=" + id + " ]";
    }

}

