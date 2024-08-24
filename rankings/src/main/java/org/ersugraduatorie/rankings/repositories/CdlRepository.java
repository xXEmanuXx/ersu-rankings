package org.ersugraduatorie.rankings.repositories;

import java.util.List;
import java.util.Optional;

import org.ersugraduatorie.rankings.models.Cdl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CdlRepository extends JpaRepository<Cdl, Long> {
    // Api controller queries

    @Query("SELECT DISTINCT c.name FROM Cdl c JOIN c.cdl_year cy WHERE cy.scholarship = true ORDER BY c.name ASC")
    public List<String> findCdlNamesWithScholarship();

    @Query("SELECT DISTINCT c.name FROM Cdl c JOIN c.cdl_year cy WHERE cy.accommodation = true ORDER BY c.name ASC")
    public List<String> findCdlNamesWithAccommodation();

    @Query("SELECT DISTINCT c.type FROM Cdl c JOIN c.cdl_year cy WHERE c.name = :name AND cy.scholarship = true ORDER BY c.type ASC")
    public List<String> findCdlTypesWithScholarship(@Param("name") String name);

    @Query("SELECT DISTINCT c.type FROM Cdl c JOIN c.cdl_year cy WHERE c.name = :name AND cy.accommodation = true ORDER BY c.type ASC")
    public List<String> findCdlTypesWithAccommodation(@Param("name") String name);

    // Rankings controller queries

    @Query("SELECT DISTINCT c FROM Cdl c JOIN c.cdl_year cy WHERE cy.scholarship = true ORDER BY c.name ASC")
    public List<Cdl> findAllWithScholarship();

    @Query("SELECT DISTINCT c FROM Cdl c JOIN c.cdl_year cy WHERE cy.accommodation = true ORDER BY c.name ASC")
    public List<Cdl> findAllWithAccommodation();

    @Query("SELECT c FROM Cdl c JOIN c.participants p WHERE p.request_number = :requestNumber")
    public Optional<Cdl> findCdlOfParticipant(@Param("requestNumber") String requestNumber);
}
