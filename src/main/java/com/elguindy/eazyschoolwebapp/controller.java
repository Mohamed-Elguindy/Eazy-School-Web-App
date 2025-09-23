package com.elguindy.eazyschoolwebapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controller {
    @RequestMapping(value={"/home","","/"})
    public String showHomePage(Model model) {
        model.addAttribute("username", "Guindy");
        return "home.html";
    }



}
