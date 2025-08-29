package org.factoriaf5.digital_academy.dto;

public class RequestAttendDTO {

    private String attendedBy;

    public RequestAttendDTO() {
    }

    public RequestAttendDTO(String attendedBy) {
        this.attendedBy = attendedBy;
    }

    public String getAttendedBy() {
        return attendedBy;
    }

    public void setAttendedBy(String attendedBy) {
        this.attendedBy = attendedBy;
    }
}
