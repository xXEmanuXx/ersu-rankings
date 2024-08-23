package org.ersugraduatorie.rankings.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "cdl", uniqueConstraints = {@UniqueConstraint(name = "cdl", columnNames = {"name", "type"})})
public class Cdl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Column(name = "type", nullable = false, length = 20)
    private String type;

    @OneToMany(mappedBy = "cdl")
    private Set<CdlYear> cdl_year;

    @OneToMany(mappedBy = "cdl")
    private Set<ParticipantSy> participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Set<CdlYear> getCdl_year() {
        return cdl_year;
    }

    public void setCdl_year(Set<CdlYear> cdl_year) {
        this.cdl_year = cdl_year;
    }

    public Set<ParticipantSy> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<ParticipantSy> participants) {
        this.participants = participants;
    }

    
}
