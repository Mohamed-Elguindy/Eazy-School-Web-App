package com.elguindy.eazyschoolwebapp.controller;

import com.elguindy.eazyschoolwebapp.model.contact;
import com.elguindy.eazyschoolwebapp.service.contactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class contactController {

    @Autowired
    private contactService contactService;

    @RequestMapping("/contact")
    public String showContactPage() {
        return "contact.html";
    }

    @PostMapping(value = "/saveMsg")
    public ModelAndView saveMsg(contact contact)
    {
        contactService.saveMessage(contact);
        return new ModelAndView("redirect:/contact");
    }

}
