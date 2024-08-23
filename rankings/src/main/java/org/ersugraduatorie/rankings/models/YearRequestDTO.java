package org.ersugraduatorie.rankings.models;

public class YearRequestDTO {
    private String requestType;
    private String cdlName;
    private String cdlType;

    public YearRequestDTO() {}

    public YearRequestDTO(String requestType, String cdlName, String cdlType) {
        this.requestType = requestType;
        this.cdlName = cdlName;
        this.cdlType = cdlType;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getCdlName() {
        return cdlName;
    }

    public void setCdlName(String cdlName) {
        this.cdlName = cdlName;
    }

    public String getCdlType() {
        return cdlType;
    }

    public void setCdlType(String cdlType) {
        this.cdlType = cdlType;
    }
} 
