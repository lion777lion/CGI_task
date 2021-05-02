package com.cgi.dentistapp.service;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.entity.DentistVisitRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class DentistVisitService {

    //most part of this was i realized in my web cv, so its was not hard
    //i try make this project with MVC principle
    public DentistVisitService(DentistVisitRepository repo) {
        this.repo = repo;
    }

    DentistVisitRepository repo;

    public void addVisit(String dentistName, Date visitTime) {
        DentistVisitEntity newVisit = new DentistVisitEntity(dentistName, visitTime);
        repo.save(newVisit);
    }

    public List<DentistVisitEntity> viewAll() {
        List<DentistVisitEntity> all = (List<DentistVisitEntity>) repo.findAll();
        return all;
    }
    //its simple way to made doctors list, but for biggest task i think need create and realize doctors entity
    public List<String> dentistList() {
        List<String> doctors = new ArrayList<String>();
        doctors.add("John Doe");
        doctors.add("Catrin Boe");
        doctors.add("Chat Goe");
        doctors.add("Anny Joe");
        return doctors;
    }

    public DentistVisitDTO toEdit(Long id) {
        DentistVisitEntity target = repo.findOne(id);
        DentistVisitDTO toEdit = new DentistVisitDTO(target.getId(), target.getDentistName(), target.getVisitTime());
        return toEdit;
    }

    public void save(DentistVisitDTO toSave) {
        DentistVisitEntity temp = repo.findOne(toSave.getTempId());
        temp.setDentistName(toSave.getDentistName());
        temp.setVisitTime(toSave.getVisitTime());
        repo.save(temp);
    }

    public void delete(Long id) {
        repo.delete(id);
    }

}
