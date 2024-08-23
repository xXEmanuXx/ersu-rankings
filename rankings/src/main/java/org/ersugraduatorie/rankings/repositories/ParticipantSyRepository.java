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
    public List<ParticipantSy> findAllWithScholarship(@Param("participantId") Long participantId);

    @Query("SELECT p FROM ParticipantSy p WHERE (p.cdl.id, p.year.id) = (SELECT p.cdl.id, p.year.id FROM ParticipantSy p WHERE p.id = :participantId) AND p.accommodation = true ORDER BY p.score DESC, p.isee ASC")
    public List<ParticipantSy> findAllWithAccommodation(@Param("participantId") Long participantId);
}
