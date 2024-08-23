package org.ersugraduatorie.rankings.repositories;

import java.util.List;

import org.ersugraduatorie.rankings.models.ParticipantFy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParticipantFyRepository extends JpaRepository<ParticipantFy, Long> {
    @Query("SELECT p.id FROM ParticipantFy p WHERE p.request_number = :requestNumber")
    public Long findParticipantId(@Param("requestNumber") String requestNumber);

    @Query("SELECT p FROM ParticipantFy p WHERE p.scholarship = true ORDER BY p.score DESC, p.ispe ASC")
    public List<ParticipantFy> findAllWithScholarship();

    @Query("SELECT p FROM ParticipantFy p WHERE p.accommodation = true ORDER BY p.score DESC, p.ispe ASC")
    public List<ParticipantFy> findAllWithAccommodation();
}
