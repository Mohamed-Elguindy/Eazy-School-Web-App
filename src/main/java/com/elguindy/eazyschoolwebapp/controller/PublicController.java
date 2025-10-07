package com.elguindy.eazyschoolwebapp.controller;

import com.elguindy.eazyschoolwebapp.model.Contact;
import com.elguindy.eazyschoolwebapp.model.Person;
import com.elguindy.eazyschoolwebapp.repositry.PersonRepository;
import com.elguindy.eazyschoolwebapp.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private PersonService  personService;

    @RequestMapping(value = "/register",method = {RequestMethod.GET})
    public String displayRegisterPage(Model model) {
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @RequestMapping("/createUser")
    public String displayCreateUser(@Valid @ModelAttribute Person person , Errors errors ) {
        if(errors.hasErrors()){
            return "register.html";
        }
        return "redirect:/login?register=true";

    }

    @RequestMapping(value ="/createUser",method = { RequestMethod.POST})
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors) {
        if(errors.hasErrors()){
            return "register.html";
        }
        boolean isSaved = personService.savePerson(person);
        if(isSaved){
            return "redirect:/login?register=true";
        }else {
            return "register.html";
        }
    }
}
