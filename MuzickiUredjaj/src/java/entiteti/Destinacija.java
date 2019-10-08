/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dusan
 */
@Entity
@Table(name = "destinacija")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Destinacija.findAll", query = "SELECT d FROM Destinacija d")
    , @NamedQuery(name = "Destinacija.findByDestinacijaId", query = "SELECT d FROM Destinacija d WHERE d.destinacijaId = :destinacijaId")
    , @NamedQuery(name = "Destinacija.findByXKoordinata", query = "SELECT d FROM Destinacija d WHERE d.xKoordinata = :xKoordinata")
    , @NamedQuery(name = "Destinacija.findByYKoordinata", query = "SELECT d FROM Destinacija d WHERE d.yKoordinata = :yKoordinata")
    , @NamedQuery(name = "Destinacija.findByNaziv", query = "SELECT d FROM Destinacija d WHERE d.naziv = :naziv")})
public class Destinacija implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "destinacija_id")
    private Integer destinacijaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "x_koordinata")
    private int xKoordinata;
    @Basic(optional = false)
    @NotNull
    @Column(name = "y_koordinata")
    private int yKoordinata;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "naziv")
    private String naziv;
    @OneToMany(mappedBy = "idD")
    private Collection<Obaveza> obavezaCollection;

    public Destinacija() {
    }

    public Destinacija(Integer destinacijaId) {
        this.destinacijaId = destinacijaId;
    }

    public Destinacija(Integer destinacijaId, int xKoordinata, int yKoordinata, String naziv) {
        this.destinacijaId = destinacijaId;
        this.xKoordinata = xKoordinata;
        this.yKoordinata = yKoordinata;
        this.naziv = naziv;
    }

    public Integer getDestinacijaId() {
        return destinacijaId;
    }

    public void setDestinacijaId(Integer destinacijaId) {
        this.destinacijaId = destinacijaId;
    }

    public int getXKoordinata() {
        return xKoordinata;
    }

    public void setXKoordinata(int xKoordinata) {
        this.xKoordinata = xKoordinata;
    }

    public int getYKoordinata() {
        return yKoordinata;
    }

    public void setYKoordinata(int yKoordinata) {
        this.yKoordinata = yKoordinata;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @XmlTransient
    public Collection<Obaveza> getObavezaCollection() {
        return obavezaCollection;
    }

    public void setObavezaCollection(Collection<Obaveza> obavezaCollection) {
        this.obavezaCollection = obavezaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (destinacijaId != null ? destinacijaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destinacija)) {
            return false;
        }
        Destinacija other = (Destinacija) object;
        if ((this.destinacijaId == null && other.destinacijaId != null) || (this.destinacijaId != null && !this.destinacijaId.equals(other.destinacijaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Destinacija[ destinacijaId=" + destinacijaId + " ]";
    }
    
}
