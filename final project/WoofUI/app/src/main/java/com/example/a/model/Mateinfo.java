package com.example.a.model;

/**
 * Created by saishree on 3/9/18.
 */
import java.util.Date;

public class Mateinfo {

    private static final long serialVersionUID = 1L;

    private Integer mateInfoId;

    private Date mateDate;

    private DogDetails dogId2;


    private DogDetails dogId;

    public Mateinfo() {
    }

    public Mateinfo(Integer mateInfoId) {
        this.mateInfoId = mateInfoId;
    }

    public Integer getMateInfoId() {
        return mateInfoId;
    }

    public void setMateInfoId(Integer mateInfoId) {
        this.mateInfoId = mateInfoId;
    }

    public Date getMateDate() {
        return mateDate;
    }

    public void setMateDate(Date mateDate) {
        this.mateDate = mateDate;
    }


//        @XmlTransient
//        public Collection<Matereq> getMatereqCollection() {
//            return matereqCollection;
//        }
//
//        public void setMatereqCollection(Collection<Matereq> matereqCollection) {
//            this.matereqCollection = matereqCollection;
//        }

    public DogDetails getDogId() {
        return dogId;
    }

    public void setDogId(DogDetails dogId) {
        this.dogId = dogId;
    }

    public DogDetails getDogId2() {
        return dogId2;
    }

    public void setDogId2(DogDetails dogId2) {
        this.dogId2 = dogId2;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mateInfoId != null ? mateInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mateinfo)) {
            return false;
        }
        Mateinfo other = (Mateinfo) object;
        if ((this.mateInfoId == null && other.mateInfoId != null) || (this.mateInfoId != null && !this.mateInfoId.equals(other.mateInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "woofw.Mateinfo[ mateInfoId=" + mateInfoId + " ]";
    }



}
