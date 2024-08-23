package org.ersugraduatorie.rankings.repositories;

import java.util.List;

import org.ersugraduatorie.rankings.models.ParticipantSy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParticipantSyRepository extends JpaRepository<ParticipantSy, Long> {
    @Query("SELECT p.id FROM ParticipantSy p WHERE p.request_number = :requestNumber")
    public Long findParticipantId(@Param("requestNumber") String requestNumber);

    @Query("SELECT p FROM ParticipantSy p  WHERE (p.cdl.id, p.year.id) = (SELECT p.cdl.id, p.year.id FROM ParticipantSy p WHERE p.id = :participantId) AND p.scholarship = true ORDER BY p.score DESC, p.isee ASC")
    public List<ParticipantSy> findAllWithScholarshipUsingParticipantId(@Param("participantId") Long participantId);

    @Query("SELECT p FROM ParticipantSy p WHERE (p.cdl.id, p.year.id) = (SELECT p.cdl.id, p.year.id FROM ParticipantSy p WHERE p.id = :participantId) AND p.accommodation = true ORDER BY p.score DESC, p.isee ASC")
    public List<ParticipantSy> findAllWithAccommodationUsingParticipantId(@Param("participantId") Long participantId);

    @Query("SELECT p FROM ParticipantSy p WHERE p.cdl.id = (SELECT c.id FROM Cdl c WHERE c.name = :cdlName AND c.type = :cdlType) AND p.year.id = (SELECT y.id FROM Year y WHERE y.year = :year) AND p.scholarship = true ORDER BY p.score DESC, p.isee ASC")
    public List<ParticipantSy> findAllWithScholarshipUsingDegree(@Param("cdlName") String cdlName, @Param("cdlType") String cdlType, @Param("year") String year);

    @Query("SELECT p FROM ParticipantSy p WHERE p.cdl.id = (SELECT c.id FROM Cdl c WHERE c.name = :cdlName AND c.type = :cdlType) AND p.year.id = (SELECT y.id FROM Year y WHERE y.year = :year) AND p.accommodation = true ORDER BY p.score DESC, p.isee ASC")
    public List<ParticipantSy> findAllWithAccommodationUsingDegree(@Param("cdlName") String cdlName, @Param("cdlType") String cdlType, @Param("year") String year);
}
