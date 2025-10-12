package com.elguindy.eazyschoolwebapp.controller;

import com.elguindy.eazyschoolwebapp.model.Person;
import com.elguindy.eazyschoolwebapp.model.Profile;
import com.elguindy.eazyschoolwebapp.service.PersonService;
import com.elguindy.eazyschoolwebapp.service.ProfileService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ProfileController {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private PersonService personService;

    @RequestMapping("/displayProfile")
    public ModelAndView displayProfile(Model model , HttpSession session) {
        Person person = (Person) session.getAttribute("LoggedInPerson");
        Profile profile = profileService.DisplayProfile(person);
        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }

    @PostMapping(value = "/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors,
                                HttpSession session) {
        if (errors.hasErrors()) {
            return "profile.html";
        }
        Person person = (Person) session.getAttribute("LoggedInPerson");
        Person savedPerson = profileService.UpdateProfile(profile, person);
        session.setAttribute("LoggedInPerson", savedPerson);
        return "redirect:/displayProfile";
    }
}
