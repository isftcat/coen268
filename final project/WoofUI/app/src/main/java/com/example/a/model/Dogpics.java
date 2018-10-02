package com.example.a.model;

/**
 * Created by saishree on 3/15/18.
 */

public class Dogpics {

    private Integer dogPicId;
   // @Size(max = 20)
    //@Column(name = "pic")
    private String pic;
   // @JoinColumn(name = "dog_id", referencedColumnName = "dog_id")
   // @ManyToOne
    private DogDetails dogId;

    public Dogpics() {
    }

    public Dogpics(Integer dogPicId) {
        this.dogPicId = dogPicId;
    }

    public Integer getDogPicId() {
        return dogPicId;
    }

    public void setDogPicId(Integer dogPicId) {
        this.dogPicId = dogPicId;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public DogDetails getDogId() {
        return dogId;
    }

    public void setDogId(DogDetails dogId) {
        this.dogId = dogId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dogPicId != null ? dogPicId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dogpics)) {
            return false;
        }
        Dogpics other = (Dogpics) object;
        if ((this.dogPicId == null && other.dogPicId != null) || (this.dogPicId != null && !this.dogPicId.equals(other.dogPicId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "web.Dogpics[ dogPicId=" + dogPicId + " ]";
    }

}
