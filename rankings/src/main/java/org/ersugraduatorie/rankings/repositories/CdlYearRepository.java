package org.ersugraduatorie.rankings.repositories;

import org.ersugraduatorie.rankings.models.CdlYear;
import org.ersugraduatorie.rankings.models.CdlYearId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CdlYearRepository extends JpaRepository<CdlYear, CdlYearId> {
    
}
