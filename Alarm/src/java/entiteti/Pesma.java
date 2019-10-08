/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dusan
 */
@Entity
@Table(name = "pesma")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pesma.findAll", query = "SELECT p FROM Pesma p")
    , @NamedQuery(name = "Pesma.findByPesmaId", query = "SELECT p FROM Pesma p WHERE p.pesmaId = :pesmaId")
    , @NamedQuery(name = "Pesma.findByNaziv", query = "SELECT p FROM Pesma p WHERE p.naziv = :naziv")})
public class Pesma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pesma_id")
    private Integer pesmaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idP")
    private Collection<Slusao> slusaoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idP")
    private Collection<Alarm> alarmCollection;

    public Pesma() {
    }

    public Pesma(Integer pesmaId) {
        this.pesmaId = pesmaId;
    }

    public Pesma(Integer pesmaId, String naziv) {
        this.pesmaId = pesmaId;
        this.naziv = naziv;
    }

    public Integer getPesmaId() {
        return pesmaId;
    }

    public void setPesmaId(Integer pesmaId) {
        this.pesmaId = pesmaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public Collection<Slusao> getSlusaoCollection() {
        return slusaoCollection;
    }

    public void setSlusaoCollection(Collection<Slusao> slusaoCollection) {
        this.slusaoCollection = slusaoCollection;
    }

    @XmlTransient
    public Collection<Alarm> getAlarmCollection() {
        return alarmCollection;
    }

    public void setAlarmCollection(Collection<Alarm> alarmCollection) {
        this.alarmCollection = alarmCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pesmaId != null ? pesmaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pesma)) {
            return false;
        }
        Pesma other = (Pesma) object;
        if ((this.pesmaId == null && other.pesmaId != null) || (this.pesmaId != null && !this.pesmaId.equals(other.pesmaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Pesma[ pesmaId=" + pesmaId + " ]";
    }
    
}
