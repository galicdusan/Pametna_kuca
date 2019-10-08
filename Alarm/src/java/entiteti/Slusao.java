/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entiteti;

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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Dusan
 */
@Entity
@Table(name = "slusao")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Slusao.findAll", query = "SELECT s FROM Slusao s")
    , @NamedQuery(name = "Slusao.findBySlusaoId", query = "SELECT s FROM Slusao s WHERE s.slusaoId = :slusaoId")})
public class Slusao implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "slusao_id")
    private Integer slusaoId;
    @JoinColumn(name = "idK", referencedColumnName = "korisnik_id")
    @ManyToOne(optional = false)
    private Korisnik idK;
    @JoinColumn(name = "idP", referencedColumnName = "pesma_id")
    @ManyToOne(optional = false)
    private Pesma idP;

    public Slusao() {
    }

    public Slusao(Integer slusaoId) {
        this.slusaoId = slusaoId;
    }

    public Integer getSlusaoId() {
        return slusaoId;
    }

    public void setSlusaoId(Integer slusaoId) {
        this.slusaoId = slusaoId;
    }

    public Korisnik getIdK() {
        return idK;
    }

    public void setIdK(Korisnik idK) {
        this.idK = idK;
    }

    public Pesma getIdP() {
        return idP;
    }

    public void setIdP(Pesma idP) {
        this.idP = idP;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (slusaoId != null ? slusaoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Slusao)) {
            return false;
        }
        Slusao other = (Slusao) object;
        if ((this.slusaoId == null && other.slusaoId != null) || (this.slusaoId != null && !this.slusaoId.equals(other.slusaoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entiteti.Slusao[ slusaoId=" + slusaoId + " ]";
    }
    
}
