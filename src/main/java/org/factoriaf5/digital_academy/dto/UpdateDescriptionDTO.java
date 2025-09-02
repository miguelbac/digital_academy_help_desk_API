package org.factoriaf5.digital_academy.dto;

public class UpdateDescriptionDTO {
    private String description;

    public UpdateDescriptionDTO() {}

    public UpdateDescriptionDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
