/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
 * @author ASUS
 */
@Entity
@Table(name = "ccondition")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ccondition.findAll", query = "SELECT c FROM Ccondition c")
    , @NamedQuery(name = "Ccondition.findByIdempcondition", query = "SELECT c FROM Ccondition c WHERE c.idempcondition = :idempcondition")
    , @NamedQuery(name = "Ccondition.findByIdemployee", query = "SELECT c FROM Ccondition c WHERE c.idemployee = :idemployee")
    , @NamedQuery(name = "Ccondition.findByDate", query = "SELECT c FROM Ccondition c WHERE c.date = :date")
    , @NamedQuery(name = "Ccondition.findByTemperature", query = "SELECT c FROM Ccondition c WHERE c.temperature = :temperature")
    , @NamedQuery(name = "Ccondition.findByIdhealty", query = "SELECT c FROM Ccondition c WHERE c.idhealty = :idhealty")})
public class Ccondition implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idempcondition")
    private Integer idempcondition;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "idemployee")
    private String idemployee;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Basic(optional = false)
    @NotNull
    @Column(name = "temperature")
    private float temperature;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "idhealty")
    private String idhealty;
    
    private String name;
    
    private String information;

    public Ccondition() {
    }

    public Ccondition(Integer idempcondition) {
        this.idempcondition = idempcondition;
    }

    public Ccondition(Integer idempcondition, String idemployee, Date date, float temperature, String idhealty) {
        this.idempcondition = idempcondition;
        this.idemployee = idemployee;
        this.date = date;
        this.temperature = temperature;
        this.idhealty = idhealty;
    }

    public Integer getIdempcondition() {
        return idempcondition;
    }

    public void setIdempcondition(Integer idempcondition) {
        this.idempcondition = idempcondition;
    }

    public String getIdemployee() {
        return idemployee;
    }

    public void setIdemployee(String idemployee) {
        this.idemployee = idemployee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getTemperature() {
        return temperature;
    }

    public void setTemperature(float temperature) {
        this.temperature = temperature;
    }

    public String getIdhealty() {
        return idhealty;
    }

    public void setIdhealty(String idhealty) {
        this.idhealty = idhealty;
    }
    
     /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
    public String getInformation() {
        return information;
    }
    
    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idempcondition != null ? idempcondition.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ccondition)) {
            return false;
        }
        Ccondition other = (Ccondition) object;
        if ((this.idempcondition == null && other.idempcondition != null) || (this.idempcondition != null && !this.idempcondition.equals(other.idempcondition))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.exerciseSpringBoot.crudBootstrap.entities.Ccondition[ idempcondition=" + idempcondition + " ]";
    }
    
}
