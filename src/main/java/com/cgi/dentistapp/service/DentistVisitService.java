package com.cgi.dentistapp.service;

import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.entity.DentistVisitRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Date;

@Service
@Transactional
public class DentistVisitService {

    public DentistVisitService(DentistVisitRepository repo) {
        this.repo = repo;
    }

    DentistVisitRepository repo;

    public void addVisit(String dentistName, Date visitTime) {
        DentistVisitEntity newVisit = new DentistVisitEntity(dentistName, visitTime);
        repo.save(newVisit);
    }
}
