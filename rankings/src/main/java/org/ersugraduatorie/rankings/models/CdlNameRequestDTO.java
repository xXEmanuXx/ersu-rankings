package org.ersugraduatorie.rankings.models;

public class CdlNameRequestDTO {
    private String requestType;

    public CdlNameRequestDTO() {}

    public CdlNameRequestDTO(String requestType) {
        this.requestType = requestType;
    }
    
    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
