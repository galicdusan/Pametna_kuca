/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dusan
 */
@Entity
@Table(name = "obaveza")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Obaveza.findAll", query = "SELECT o FROM Obaveza o")
    , @NamedQuery(name = "Obaveza.findByObavezaId", query = "SELECT o FROM Obaveza o WHERE o.obavezaId = :obavezaId")
    , @NamedQuery(name = "Obaveza.findByVremePocetka", query = "SELECT o FROM Obaveza o WHERE o.vremePocetka = :vremePocetka")
    , @NamedQuery(name = "Obaveza.findByNaziv", query = "SELECT o FROM Obaveza o WHERE o.naziv = :naziv")})
public class Obaveza implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "obaveza_id")
    private Integer obavezaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vreme_pocetka")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremePocetka;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "naziv")
    private String naziv;
    @JoinColumn(name = "idA", referencedColumnName = "alarm_id")
    @ManyToOne
    private Alarm idA;
    @JoinColumn(name = "idD", referencedColumnName = "destinacija_id")
    @ManyToOne
    private Destinacija idD;

    public Obaveza() {
    }

    public Obaveza(Integer obavezaId) {
        this.obavezaId = obavezaId;
    }

    public Obaveza(Integer obavezaId, Date vremePocetka, String naziv) {
        this.obavezaId = obavezaId;
        this.vremePocetka = vremePocetka;
        this.naziv = naziv;
    }

    public Integer getObavezaId() {
        return obavezaId;
    }

    public void setObavezaId(Integer obavezaId) {
        this.obavezaId = obavezaId;
    }

    public Date getVremePocetka() {
        return vremePocetka;
    }

    public void setVremePocetka(Date vremePocetka) {
        this.vremePocetka = vremePocetka;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public Alarm getIdA() {
        return idA;
    }

    public void setIdA(Alarm idA) {
        this.idA = idA;
    }

    public Destinacija getIdD() {
        return idD;
    }

    public void setIdD(Destinacija idD) {
        this.idD = idD;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (obavezaId != null ? obavezaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Obaveza)) {
            return false;
        }
        Obaveza other = (Obaveza) object;
        if ((this.obavezaId == null && other.obavezaId != null) || (this.obavezaId != null && !this.obavezaId.equals(other.obavezaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Obaveza[ obavezaId=" + obavezaId + " ]";
    }
    
}
