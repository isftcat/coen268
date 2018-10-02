/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author saishree
 */@Entity
@Table(name = "Dog_Details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DogDetails.findAll", query = "SELECT d FROM DogDetails d")
    , @NamedQuery(name = "DogDetails.findByDogId", query = "SELECT d FROM DogDetails d WHERE d.dogId = :dogId")
    , @NamedQuery(name = "DogDetails.findByName", query = "SELECT d FROM DogDetails d WHERE d.name = :name")
    , @NamedQuery(name = "DogDetails.findByDob", query = "SELECT d FROM DogDetails d WHERE d.dob = :dob")
    , @NamedQuery(name = "DogDetails.findByPic", query = "SELECT d FROM DogDetails d WHERE d.pic = :pic")
    , @NamedQuery(name = "DogDetails.findByBreed", query = "SELECT d FROM DogDetails d WHERE d.breed = :breed")})
public class DogDetails implements Serializable {


    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dog_id")
    private Integer dogId;
    
    
    
    @Size(max = 20)
    @Column(name = "gender")
    private String gender;
    
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Column(name = "DOB")
    @Temporal(TemporalType.DATE)
    private Date dob;
    @Size(max = 100)
    @Column(name = "pic")
    private String pic;
    @Size(max = 50)
    @Column(name = "breed")
    private String breed;
    @OneToMany(mappedBy = "dogId")
    private Collection<Matereq> matereqCollection;
    @OneToMany(mappedBy = "dogId")
    private Collection<WalkInfo> walkInfoCollection;
    @OneToMany(mappedBy = "dogId")
    private Collection<Mateinfo> mateinfoCollection;
    @OneToMany(mappedBy = "dogId2")
    private Collection<Mateinfo> mateinfoCollection1;
    @OneToMany(mappedBy = "dogId")
    private Collection<Dogpics> dogpicsCollection;
    @JoinColumn(name = "owner_id", referencedColumnName = "owner_id")
    @ManyToOne
     private OwnerDetails ownerId;

    public DogDetails() {
    }

    public DogDetails(Integer dogId) {
        this.dogId = dogId;
    }

    public Integer getDogId() {
        return dogId;
    }

    public void setDogId(Integer dogId) {
        this.dogId = dogId;
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    @XmlTransient
    public Collection<Matereq> getMatereqCollection() {
        return matereqCollection;
    }

    public void setMatereqCollection(Collection<Matereq> matereqCollection) {
        this.matereqCollection = matereqCollection;
    }

    @XmlTransient
    public Collection<WalkInfo> getWalkInfoCollection() {
        return walkInfoCollection;
    }

    public void setWalkInfoCollection(Collection<WalkInfo> walkInfoCollection) {
        this.walkInfoCollection = walkInfoCollection;
    }

    @XmlTransient
    public Collection<Mateinfo> getMateinfoCollection() {
        return mateinfoCollection;
    }

    public void setMateinfoCollection(Collection<Mateinfo> mateinfoCollection) {
        this.mateinfoCollection = mateinfoCollection;
    }

    @XmlTransient
    public Collection<Mateinfo> getMateinfoCollection1() {
        return mateinfoCollection1;
    }

    public void setMateinfoCollection1(Collection<Mateinfo> mateinfoCollection1) {
        this.mateinfoCollection1 = mateinfoCollection1;
    }

    @XmlTransient
    public Collection<Dogpics> getDogpicsCollection() {
        return dogpicsCollection;
    }

    public void setDogpicsCollection(Collection<Dogpics> dogpicsCollection) {
        this.dogpicsCollection = dogpicsCollection;
    }

    public OwnerDetails getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(OwnerDetails ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dogId != null ? dogId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DogDetails)) {
            return false;
        }
        DogDetails other = (DogDetails) object;
        if ((this.dogId == null && other.dogId != null) || (this.dogId != null && !this.dogId.equals(other.dogId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "woofw.DogDetails[ dogId=" + dogId + " ]";
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
}
