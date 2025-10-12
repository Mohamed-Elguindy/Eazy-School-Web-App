package com.elguindy.eazyschoolwebapp.controller;

import com.elguindy.eazyschoolwebapp.model.Person;
import com.elguindy.eazyschoolwebapp.repositry.PersonRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class DashboardController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/dashboard")
    public String displayDashboard(Model model, Authentication authentication , HttpSession session) {
        Person person = personRepository.readByEmail(authentication.getName()); // Authentication name -> Email
        model.addAttribute("username", person.getName());
        model.addAttribute("roles", authentication.getAuthorities().toString());
        session.setAttribute("LoggedInPerson", person);
        return "dashboard.html";
    }
}
