package org.ersugraduatorie.rankings.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "participant_fy")
public class ParticipantFy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "request_number", nullable = false, length = 10, unique = true)
    private String request_number;

    @Column(name = "isee", nullable = true)
    private Double isee;

    @Column(name = "ispe", nullable = true)
    private Double ispe;

    @Column(name = "score", nullable = false)
    private Double score;

    @Column(name = "notes", nullable = true, length = 255)
    private String notes;

    @Column(name = "scholarship", nullable = false)
    private Boolean scholarship;

    @Column(name = "accommodation", nullable = false)
    private Boolean accommodation;

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

    public Double getIsee() {
        return isee;
    }

    public void setIsee(Double isee) {
        this.isee = isee;
    }

    public Double getIspe() {
        return ispe;
    }

    public void setIspe(Double ispe) {
        this.ispe = ispe;
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
}
