package org.ersugraduatorie.rankings.repositories;

import java.util.List;

import org.ersugraduatorie.rankings.models.ParticipantSy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ParticipantSyRepository extends JpaRepository<ParticipantSy, Long> {
    @Query("SELECT p FROM ParticipantSy p JOIN ParticipantSy pRef ON p.cdl.id = pRef.cdl.id AND p.year.id = pRef.year.id WHERE pRef.request_number = :requestNumber AND p.scholarship = true ORDER BY p.score DESC, p.isee ASC")
    public List<ParticipantSy> findAllWithScholarshipUsingRequestNumber(@Param("requestNumber") String requestNumber);

    @Query("SELECT p FROM ParticipantSy p JOIN ParticipantSy pRef ON p.cdl.id = pRef.cdl.id AND p.year.id = pRef.year.id WHERE pRef.request_number = :requestNumber AND p.accommodation = true ORDER BY p.score DESC, p.isee ASC")
    public List<ParticipantSy> findAllWithAccommodationUsingRequestNumber(@Param("requestNumber") String requestNumber);

    @Query("SELECT p FROM ParticipantSy p WHERE p.cdl.id = (SELECT c.id FROM Cdl c WHERE c.name = :cdlName AND c.type = :cdlType) AND p.year.id = (SELECT y.id FROM Year y WHERE y.year = :year) AND p.scholarship = true ORDER BY p.score DESC, p.isee ASC")
    public List<ParticipantSy> findAllWithScholarshipUsingDegree(@Param("cdlName") String cdlName, @Param("cdlType") String cdlType, @Param("year") String year);

    @Query("SELECT p FROM ParticipantSy p WHERE p.cdl.id = (SELECT c.id FROM Cdl c WHERE c.name = :cdlName AND c.type = :cdlType) AND p.year.id = (SELECT y.id FROM Year y WHERE y.year = :year) AND p.accommodation = true ORDER BY p.score DESC, p.isee ASC")
    public List<ParticipantSy> findAllWithAccommodationUsingDegree(@Param("cdlName") String cdlName, @Param("cdlType") String cdlType, @Param("year") String year);

    @Query("SELECT p FROM ParticipantSy p WHERE p.cdl.id = :cdlId AND p.year.id = :yearId AND p.scholarship = true ORDER BY p.score DESC, p.isee ASC")
    public List<ParticipantSy> findAllWithScholarshipUsingManual(@Param("cdlId") Long cdlId, @Param("yearId") Long yearId);

    @Query("SELECT p FROM ParticipantSy p WHERE p.cdl.id = :cdlId AND p.year.id = :yearId AND p.accommodation = true ORDER BY p.score DESC, p.isee ASC")
    public List<ParticipantSy> findAllWithAccommodationUsingManual(@Param("cdlId") Long cdlId, @Param("yearId") Long yearId);
}
