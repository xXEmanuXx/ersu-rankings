package org.ersugraduatorie.rankings.repositories;

import java.util.List;

import org.ersugraduatorie.rankings.models.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface YearRepository extends JpaRepository<Year, Long> {
    @Query("SELECT DISTINCT y.year FROM Year y JOIN y.cdl_year cy WHERE cy.cdlId = :cdlId AND cy.scholarship = true")
    public List<String> findYearsWithScholarship(@Param("cdlId") Long cdlId);

    @Query("SELECT DISTINCT y.year FROM Year y JOIN y.cdl_year cy WHERE cy.cdlId = :cdlId AND cy.accommodation = true")
    public List<String> findYearsWithAccommodation(@Param("cdlId") Long cdlId);

    @Query("SELECT y FROM Year y JOIN y.participants p WHERE p.id = :participantId")
    public Year findYearOfParticipant(@Param("participantId") Long participantId);
}
