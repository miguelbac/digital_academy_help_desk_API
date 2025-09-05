package org.factoriaf5.digital_academy.dto;

public class ReassignRequesterDTO {
    private String requesterName;

    public ReassignRequesterDTO() {
    }

    public ReassignRequesterDTO(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }
}
