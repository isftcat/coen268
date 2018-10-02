/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package web;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author saishree
 */
@Entity
@Table(name = "Dog_pics")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dogpics.findAll", query = "SELECT d FROM Dogpics d")
    , @NamedQuery(name = "Dogpics.findByDogPicId", query = "SELECT d FROM Dogpics d WHERE d.dogPicId = :dogPicId")
    , @NamedQuery(name = "Dogpics.findByPic", query = "SELECT d FROM Dogpics d WHERE d.pic = :pic")})
public class Dogpics implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "dog_pic_id")
    private Integer dogPicId;
    @Size(max = 20)
    @Column(name = "pic")
    private String pic;
    @JoinColumn(name = "dog_id", referencedColumnName = "dog_id")
    @ManyToOne
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
