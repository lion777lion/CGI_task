package com.cgi.dentistapp.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class DentistVisitDTO {

    public DentistVisitDTO(Long id, String dentistName, Date visitTime) {
        this.tempId = id;
        this.dentistName = dentistName;
        this.visitTime = visitTime;
    }


    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    Long tempId;

    @Size(min = 1, max = 50)
    String dentistName;

    //i didnt find way to make date unic and with time space
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    Date visitTime;

    public DentistVisitDTO() {
    }

    public DentistVisitDTO(String dentistName, Date visitTime) {
        this.dentistName = dentistName;
        this.visitTime = visitTime;

    }

    public String getDentistName() {
        return dentistName;
    }

    public void setDentistName(String dentistName) {
        this.dentistName = dentistName;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }
}
