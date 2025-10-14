package com.elguindy.eazyschoolwebapp.controller;

import com.elguindy.eazyschoolwebapp.model.Contact;
import com.elguindy.eazyschoolwebapp.repositry.ContactRepositry;
import com.elguindy.eazyschoolwebapp.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Slf4j
@Controller
public class contactController  {

    @Autowired
    private ContactService contactService;

    @RequestMapping("/contact")
    public String showContactPage(Model model) {
        model.addAttribute("contact",new Contact());
        return "contact.html";
    }

    @PostMapping(value = "/saveMsg")
    public String saveMsg(@Valid @ModelAttribute("contact") Contact contact, Errors errors)
    {
        if(errors.hasErrors()){
            log.error("contact form has errors due to : "+errors.toString());
            return "contact.html";
        }

        contactService.saveMessage(contact);
        return "redirect:/contact";
    }

    @RequestMapping("/displayMessages/page/{pageNum}")
    public ModelAndView displayMessages(Model model,
                                        @PathVariable(name = "pageNum") int pageNum, @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir) {
        Page<Contact> msgPage = contactService.findMsgsWithOpenStatus(pageNum,sortField,sortDir);
        List<Contact> contactMsgs = msgPage.getContent();
        ModelAndView modelAndView = new ModelAndView("messages.html");
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", msgPage.getTotalPages());
        model.addAttribute("totalMsgs", msgPage.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        modelAndView.addObject("contactMsgs",contactMsgs);
        return modelAndView;
    }

    @RequestMapping(value = "/closeMsg",method = GET)
    public String closeMsg(@RequestParam int id) {
        contactService.updateMsgStatus(id);
        return "redirect:/displayMessages/page/1?sortField=name&sortDir=desc";
    }

}
