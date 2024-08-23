package org.ersugraduatorie.rankings.models;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "year")
public class Year {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "year", nullable = false, length = 3, unique = true)
    private String year;

    @OneToMany(mappedBy = "year")
    private Set<CdlYear> cdl_year;

    @OneToMany(mappedBy = "year")
    private Set<ParticipantSy> participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
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
