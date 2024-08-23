package org.ersugraduatorie.rankings.models;

import java.io.Serializable;

public class CdlYearId implements Serializable {
    private Long cdlId;
    private Long yearId;

    public CdlYearId() {}

    public CdlYearId(Long cdlId, Long yearId) {
        this.cdlId = cdlId;
        this.yearId = yearId;
    }
    
    public Long getCdlId() {
        return cdlId;
    }

    public void setCdlId(Long cdlId) {
        this.cdlId = cdlId;
    }

    public Long getYearId() {
        return yearId;
    }

    public void setYearId(Long yearId) {
        this.yearId = yearId;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
