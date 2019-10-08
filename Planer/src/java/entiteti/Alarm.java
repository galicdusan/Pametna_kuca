/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Dusan
 */
@Entity
@Table(name = "alarm")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Alarm.findAll", query = "SELECT a FROM Alarm a")
    , @NamedQuery(name = "Alarm.findByAlarmId", query = "SELECT a FROM Alarm a WHERE a.alarmId = :alarmId")
    , @NamedQuery(name = "Alarm.findByVremeAktivacije", query = "SELECT a FROM Alarm a WHERE a.vremeAktivacije = :vremeAktivacije")
    , @NamedQuery(name = "Alarm.findByPerioda", query = "SELECT a FROM Alarm a WHERE a.perioda = :perioda")})
public class Alarm implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "alarm_id")
    private Integer alarmId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "vreme_aktivacije")
    @Temporal(TemporalType.TIMESTAMP)
    private Date vremeAktivacije;
    @Column(name = "perioda")
    private Integer perioda;
    @JoinColumn(name = "idP", referencedColumnName = "pesma_id")
    @ManyToOne(optional = false)
    private Pesma idP;
    @OneToMany(mappedBy = "idA")
    private Collection<Obaveza> obavezaCollection;

    public Alarm() {
    }

    public Alarm(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public Alarm(Integer alarmId, Date vremeAktivacije) {
        this.alarmId = alarmId;
        this.vremeAktivacije = vremeAktivacije;
    }

    public Integer getAlarmId() {
        return alarmId;
    }

    public void setAlarmId(Integer alarmId) {
        this.alarmId = alarmId;
    }

    public Date getVremeAktivacije() {
        return vremeAktivacije;
    }

    public void setVremeAktivacije(Date vremeAktivacije) {
        this.vremeAktivacije = vremeAktivacije;
    }

    public Integer getPerioda() {
        return perioda;
    }

    public void setPerioda(Integer perioda) {
        this.perioda = perioda;
    }

    public Pesma getIdP() {
        return idP;
    }

    public void setIdP(Pesma idP) {
        this.idP = idP;
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
        hash += (alarmId != null ? alarmId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Alarm)) {
            return false;
        }
        Alarm other = (Alarm) object;
        if ((this.alarmId == null && other.alarmId != null) || (this.alarmId != null && !this.alarmId.equals(other.alarmId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Alarm[ alarmId=" + alarmId + " ]";
    }
    
}
