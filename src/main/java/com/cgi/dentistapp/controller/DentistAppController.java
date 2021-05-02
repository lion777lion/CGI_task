package com.cgi.dentistapp.controller;

import com.cgi.dentistapp.dto.DentistVisitDTO;
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

@Controller
@EnableAutoConfiguration
public class DentistAppController extends WebMvcConfigurerAdapter {

    @Autowired
    private DentistVisitService dentistVisitService;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegisterForm(DentistVisitDTO dentistVisitDTO, Model model) {
        model.addAttribute("dentistVisitDTO", dentistVisitDTO);
        model.addAttribute("allDentist", dentistVisitService.dentistList());
        return "form";
    }

    @PostMapping("/register")
    public String postRegisterForm(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("allDentist", dentistVisitService.dentistList());
            return "form";
        }
        dentistVisitService.addVisit(dentistVisitDTO.getDentistName(), dentistVisitDTO.getVisitTime());
        return "redirect:/results";
    }

    @GetMapping("/list")
    public String showAll(Model model) {
        model.addAttribute("listAll", dentistVisitService.viewAll());
        return ("list");
    }

    @GetMapping("/edit")
    public String toEdit(@RequestParam Long edit, Model model) {
        model.addAttribute("allDentist", dentistVisitService.dentistList());
        model.addAttribute("toEdit", dentistVisitService.toEdit(edit));
        return ("edit");
    }

    @PostMapping("/edit")
    public String editing(@Valid DentistVisitDTO dentistVisitDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        dentistVisitService.save(dentistVisitDTO);
        return ("redirect:/list");
    }

    @PostMapping
    @RequestMapping("/delete")
    public String deleteData(@RequestParam Long delete) {
        dentistVisitService.delete(delete);
        return ("redirect:/list");
    }
}
