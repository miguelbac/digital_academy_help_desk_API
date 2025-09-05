package org.factoriaf5.digital_academy.dto;

public class UpdateStatusDTO {
    private String status;
    private String technician;

    public UpdateStatusDTO() {
    }

    public UpdateStatusDTO(String status, String technician) {
        this.status = status;
        this.technician = technician;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTechnician() {
        return technician;
    }

    public void setTechnician(String technician) {
        this.technician = technician;
    }
}
