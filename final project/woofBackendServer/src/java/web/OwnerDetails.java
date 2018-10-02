/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author saishree
 */
@Entity
@Table(name = "Owner_Details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OwnerDetails.findAll", query = "SELECT o FROM OwnerDetails o")
    , @NamedQuery(name = "OwnerDetails.findByOwnerId", query = "SELECT o FROM OwnerDetails o WHERE o.ownerId = :ownerId")
    , @NamedQuery(name = "OwnerDetails.findByName", query = "SELECT o FROM OwnerDetails o WHERE o.name = :name")
    , @NamedQuery(name = "OwnerDetails.findByAddress", query = "SELECT o FROM OwnerDetails o WHERE o.address = :address")
    , @NamedQuery(name = "OwnerDetails.findByCity", query = "SELECT o FROM OwnerDetails o WHERE o.city = :city")
    , @NamedQuery(name = "OwnerDetails.findByState", query = "SELECT o FROM OwnerDetails o WHERE o.state = :state")
    , @NamedQuery(name = "OwnerDetails.findByCountry", query = "SELECT o FROM OwnerDetails o WHERE o.country = :country")
    , @NamedQuery(name = "OwnerDetails.findByProfilepic", query = "SELECT o FROM OwnerDetails o WHERE o.profilepic = :profilepic")
    , @NamedQuery(name = "OwnerDetails.findByOwnerEmail", query = "SELECT o FROM OwnerDetails o WHERE o.ownerEmail = :ownerEmail")
    , @NamedQuery(name = "OwnerDetails.findByOwnerMobile", query = "SELECT o FROM OwnerDetails o WHERE o.ownerMobile = :ownerMobile")
    , @NamedQuery(name = "OwnerDetails.findByPassword", query = "SELECT o FROM OwnerDetails o WHERE o.password = :password")
    , @NamedQuery(name = "OwnerDetails.findByZipcode", query = "SELECT o FROM OwnerDetails o WHERE o.zipcode = :zipcode")
    , @NamedQuery(name = "OwnerDetails.findByToken", query = "SELECT o FROM OwnerDetails o WHERE o.token = :token")})
public class OwnerDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "owner_id")
    private Integer ownerId;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 150)
    @Column(name = "address")
    private String address;
    @Size(max = 50)
    @Column(name = "city")
    private String city;
    @Size(max = 50)
    @Column(name = "state")
    private String state;
    @Size(max = 50)
    @Column(name = "country")
    private String country;
    @Size(max = 100)
    @Column(name = "profilepic")
    private String profilepic;
    @Size(max = 20)
    @Column(name = "owner_email")
    private String ownerEmail;
    @Size(max = 20)
    @Column(name = "owner_mobile")
    private String ownerMobile;
    @Size(max = 20)
    @Column(name = "password")
    private String password;
    @Column(name = "zipcode")
    private Integer zipcode;
    @Size(max = 65000)
    @Column(name = "token")
    private String token;
    @OneToMany(mappedBy = "walkerId")
    private Collection<WalkInfo> walkInfoCollection;
    @OneToMany(mappedBy = "walkerId")
    private Collection<WalkReq> walkReqCollection;
    @OneToMany(mappedBy = "ownerId")
    private Collection<DogDetails> dogDetailsCollection;

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

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getOwnerMobile() {
        return ownerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        this.ownerMobile = ownerMobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getZipcode() {
        return zipcode;
    }

    public void setZipcode(Integer zipcode) {
        this.zipcode = zipcode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @XmlTransient
    public Collection<WalkInfo> getWalkInfoCollection() {
        return walkInfoCollection;
    }

    public void setWalkInfoCollection(Collection<WalkInfo> walkInfoCollection) {
        this.walkInfoCollection = walkInfoCollection;
    }

    @XmlTransient
    public Collection<WalkReq> getWalkReqCollection() {
        return walkReqCollection;
    }

    public void setWalkReqCollection(Collection<WalkReq> walkReqCollection) {
        this.walkReqCollection = walkReqCollection;
    }

    @XmlTransient
    public Collection<DogDetails> getDogDetailsCollection() {
        return dogDetailsCollection;
    }

    public void setDogDetailsCollection(Collection<DogDetails> dogDetailsCollection) {
        this.dogDetailsCollection = dogDetailsCollection;
    }

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
        return "woofw.OwnerDetails[ ownerId=" + ownerId + " ]";
    }
    
}
