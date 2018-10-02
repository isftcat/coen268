package com.example.a.model;

/**
 * Created by A on 3/6/2018.
 */

public class OwnerDetails {

    private static final long serialVersionUID = 1L;

    private Integer ownerId;

    private String name;

    private String address;

    private String city;

    private String state;

    private String country;

    private String profilepic;

    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation

    private String email;

    private String mobile;

//    private Collection<DogDetails> dogDetailsCollection;
//    private Collection<WalkInfo> walkInfoCollection;
//    @OneToMany(mappedBy = "walkerId")
//    private Collection<WalkReq> walkReqCollection;

    public OwnerDetails() {
    }

    public OwnerDetails(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

//    @XmlTransient
//    public Collection<DogDetails> getDogDetailsCollection() {
//        return dogDetailsCollection;
//    }
//
//    public void setDogDetailsCollection(Collection<DogDetails> dogDetailsCollection) {
//        this.dogDetailsCollection = dogDetailsCollection;
//    }
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
//    public Collection<WalkReq> getWalkReqCollection() {
//        return walkReqCollection;
//    }
//
//    public void setWalkReqCollection(Collection<WalkReq> walkReqCollection) {
//        this.walkReqCollection = walkReqCollection;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ownerId != null ? ownerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OwnerDetails)) {
            return false;
        }
        OwnerDetails other = (OwnerDetails) object;
        if ((this.ownerId == null && other.ownerId != null) || (this.ownerId != null && !this.ownerId.equals(other.ownerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "webwoof.OwnerDetails[ ownerId=" + ownerId + " ]";
    }

}
