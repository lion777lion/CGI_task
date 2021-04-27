package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
import com.cgi.dentistapp.entity.DentistVisitEntity;
import com.cgi.dentistapp.entity.DentistVisitRepository;
import com.cgi.dentistapp.service.DentistVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;
import java.util.List;

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

    public DentistAppController(DentistVisitRepository repo) {
        this.repo = repo;
    }

    DentistVisitRepository repo;

    @Autowired
    private DentistVisitService dentistVisitService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/register")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO) {
        return "form";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/register")
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        dentistVisitService.addVisit(dentistVisitDTO.getDentistName(), dentistVisitDTO.getVisitTime());
        return "redirect:/results";
    }

    @GetMapping("/list")
    public String showAll(Model model) {
        List<DentistVisitEntity> listAll = (List<DentistVisitEntity>) repo.findAll();
        model.addAttribute("listAll", listAll);
        return ("list");
    }

    @GetMapping("/edit")
    public String toEdit(@RequestParam Long edit, Model model){
        System.out.println(edit);
        DentistVisitEntity temp = repo.findOne(edit);
        System.out.println(temp.getDentistName());
        DentistVisitDTO toEdit = new DentistVisitDTO(temp.getId(), temp.getDentistName(), temp.getVisitTime());
        System.out.println(toEdit.getTempId());
        model.addAttribute("toEdit", toEdit);
        return ("edit");
    }

    @PostMapping("/edit")
    public String editing(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        System.out.println(dentistVisitDTO.getTempId());
        DentistVisitEntity temp = repo.findOne(dentistVisitDTO.getTempId());
        temp.setDentistName(dentistVisitDTO.getDentistName());
        temp.setVisitTime(dentistVisitDTO.getVisitTime());
        repo.save(temp);
        return ("redirect:/list");
    }

    @PostMapping
    @RequestMapping("/delete")
    public String deleteData(@RequestParam Long delete) {
        repo.delete(delete);
        return ("redirect:/list");
    }
}
