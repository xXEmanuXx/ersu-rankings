package org.ersugraduatorie.rankings.models;

public class CdlTypeRequestDTO {
    private String requestType;
    private String cdlName;

    public CdlTypeRequestDTO() {}

    public CdlTypeRequestDTO(String requestType, String cdlName) {
        this.requestType = requestType;
        this.cdlName = cdlName;
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
}
