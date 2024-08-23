package org.ersugraduatorie.rankings.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "participant_sy")
public class ParticipantSy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "request_number", nullable = false, length = 10, unique = true)
    private String request_number;

    @Column(name = "cfu", nullable = true)
    private Integer cfu;

    @Column(name = "average", nullable = true)
    private Double average;

    @Column(name = "honors", nullable = true)
    private Integer honors;

    @Column(name = "bonus", nullable = false)
    private Integer bonus;

    @Column(name = "score", nullable = false)
    private Double score;

    @Column(name = "notes", nullable = true, length = 255)
    private String notes;

    @Column(name = "isee", nullable = true)
    private Double isee;

    @Column(name = "scholarship", nullable = false)
    private Boolean scholarship;

    @Column(name = "accommodation", nullable = false)
    private Boolean accommodation;

    @ManyToOne
    @JoinColumn(name = "cdl", referencedColumnName = "id")
    private Cdl cdl;

    @ManyToOne
    @JoinColumn(name = "year", referencedColumnName = "id")
    private Year year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequest_number() {
        return request_number;
    }

    public void setRequest_number(String request_number) {
        this.request_number = request_number;
    }

    public Integer getCfu() {
        return cfu;
    }

    public void setCfu(Integer cfu) {
        this.cfu = cfu;
    }

    public Double getAverage() {
        return average;
    }

    public void setAverage(Double average) {
        this.average = average;
    }

    public Integer getHonors() {
        return honors;
    }

    public void setHonors(Integer honors) {
        this.honors = honors;
    }

    public Integer getBonus() {
        return bonus;
    }

    public void setBonus(Integer bonus) {
        this.bonus = bonus;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getIsee() {
        return isee;
    }

    public void setIsee(Double isee) {
        this.isee = isee;
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
