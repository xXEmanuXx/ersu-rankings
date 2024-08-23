package org.ersugraduatorie.rankings.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;

@Entity
@Table(name = "cdl_year")
@IdClass(CdlYearId.class)
public class CdlYear {
    @Id
    @Column(name = "cdl")
    private Long cdlId;

    @Id
    @Column(name = "year")
    private Long yearId;

    @Column(name = "scholarship", nullable = false)
    private Boolean scholarship;

    @Column(name = "accommodation", nullable = false)
    private Boolean accommodation;

    @ManyToOne
    @MapsId("cdlId")
    @JoinColumn(name = "cdl", referencedColumnName = "id")
    private Cdl cdl;

    @ManyToOne
    @MapsId("yearId")
    @JoinColumn(name = "year", referencedColumnName = "id")
    private Year year;

    public Long getCdlId() {
        return cdlId;
    }

    public void setCdlId(Long cdlId) {
        this.cdlId = cdlId;
    }

    public Long getYearId() {
        return yearId;
    }

    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    public Boolean getScholarship() {
        return scholarship;
    }

    public void setScholarship(Boolean scholarship) {
        this.scholarship = scholarship;
    }

    public Boolean getAccommodation() {
        return accommodation;
    }

    public void setAccommodation(Boolean accommodation) {
        this.accommodation = accommodation;
    }

    public Cdl getCdl() {
        return cdl;
    }

    public void setCdl(Cdl cdl) {
        this.cdl = cdl;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    
}
