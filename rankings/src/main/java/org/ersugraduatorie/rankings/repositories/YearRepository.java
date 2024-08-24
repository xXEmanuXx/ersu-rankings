package org.ersugraduatorie.rankings.repositories;

import java.util.List;
import java.util.Optional;

import org.ersugraduatorie.rankings.models.Year;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface YearRepository extends JpaRepository<Year, Long> {
    // Api controller queries
    
    @Query("SELECT DISTINCT y.year FROM Year y JOIN y.cdl_year cy WHERE cy.cdlId = (SELECT c.id FROM Cdl c WHERE c.name = :cdlName AND c.type = :cdlType) AND cy.scholarship = true ORDER BY y.year ASC")
    public List<String> findYearsWithScholarship(@Param("cdlName") String cdlName, @Param("cdlType") String cdlType);

    @Query("SELECT DISTINCT y.year FROM Year y JOIN y.cdl_year cy WHERE cy.cdlId = (SELECT c.id FROM Cdl c WHERE c.name = :cdlName AND c.type = :cdlType) AND cy.accommodation = true ORDER BY y.year ASC")
    public List<String> findYearsWithAccommodation(@Param("cdlName") String cdlName, @Param("cdlType") String cdlType);

    // Rankings controller queries

    @Query("SELECT DISTINCT y FROM Year y JOIN y.cdl_year cy WHERE cy.cdlId = :cdlId AND cy.scholarship = true ORDER BY y.year ASC")
    public List<Year> findAllWithScholarship(@Param("cdlId") Long cdlId);

    @Query("SELECT DISTINCT y FROM Year y JOIN y.cdl_year cy WHERE cy.cdlId = :cdlId AND cy.accommodation = true ORDER BY y.year ASC")
    public List<Year> findAllWithAccommodation(@Param("cdlId") Long cdlId);

    @Query("SELECT y FROM Year y JOIN y.participants p WHERE p.request_number = :requestNumber")
    public Optional<Year> findYearOfParticipant(@Param("requestNumber") String requestNumber);
}
