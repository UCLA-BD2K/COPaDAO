package org.copakb.server.dao.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;


/**
 * Created by Kevin on 5/1/2015.
 */
@Entity
@Table(name = "Version")
public class Version {
    private int version;
    private Date date;
    private String description;
    private Set<ProteinHistory> proteinHistories = new HashSet<ProteinHistory>(0);

    public Version(int version, Date date, String description, Set<ProteinHistory> proteinHistories) {
        this.version = version;
        this.date = date;
        this.description = description;
        this.proteinHistories = proteinHistories;
    }

    public Version() {
        //default
    }

    @Id
    @Column(name = "version")
    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "version")
    public Set<ProteinHistory> getProteinHistories() {
        return proteinHistories;
    }
    public void setProteinHistories(Set<ProteinHistory> proteinHistories) {
        this.proteinHistories = proteinHistories;
    }
}
