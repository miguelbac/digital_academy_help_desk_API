package org.factoriaf5.digital_academy.dto;

public class UpdateStatusDTO {
    private String status;

    public UpdateStatusDTO() {}

    public UpdateStatusDTO(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
