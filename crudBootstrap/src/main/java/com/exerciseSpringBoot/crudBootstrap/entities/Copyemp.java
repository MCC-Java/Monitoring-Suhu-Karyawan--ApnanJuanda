/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.exerciseSpringBoot.crudBootstrap.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "copyemp")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Copyemp.findAll", query = "SELECT c FROM Copyemp c")
    , @NamedQuery(name = "Copyemp.findById", query = "SELECT c FROM Copyemp c WHERE c.id = :id")
    , @NamedQuery(name = "Copyemp.findByIdjob", query = "SELECT c FROM Copyemp c WHERE c.idjob = :idjob")
    , @NamedQuery(name = "Copyemp.findByName", query = "SELECT c FROM Copyemp c WHERE c.name = :name")
    , @NamedQuery(name = "Copyemp.findByGroup", query = "SELECT c FROM Copyemp c WHERE c.group = :group")})
public class Copyemp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 11)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "idjob")
    private String idjob;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "group")
    private String group;
    
    private String jobtitle;

    public Copyemp() {
    }

    public Copyemp(String id) {
        this.id = id;
    }

    public Copyemp(String id, String idjob, String name, String group) {
        this.id = id;
        this.idjob = idjob;
        this.name = name;
        this.group = group;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdjob() {
        return idjob;
    }

    public void setIdjob(String idjob) {
        this.idjob = idjob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Copyemp)) {
            return false;
        }
        Copyemp other = (Copyemp) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id ;
    }
    
    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }
    
}
