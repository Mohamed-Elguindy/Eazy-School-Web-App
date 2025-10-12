package com.elguindy.eazyschoolwebapp.controller;

import com.elguindy.eazyschoolwebapp.model.EazyClass;
import com.elguindy.eazyschoolwebapp.model.Person;
import com.elguindy.eazyschoolwebapp.repositry.EazyClassRepository;
import com.elguindy.eazyschoolwebapp.repositry.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    EazyClassRepository eazyClassRepository;

    @Autowired
    PersonRepository personRepository;


    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<EazyClass> eazyClasses = eazyClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("eazyClasses",eazyClasses);
        modelAndView.addObject("eazyClass", new EazyClass());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public String addNewClass(EazyClass eazyClass) {
        eazyClassRepository.save(eazyClass);
        return "redirect:/admin/displayClasses";
    }

    @RequestMapping("/deleteClass")
    public String deleteClass(@RequestParam int id) {
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(id);
        if(eazyClass.isPresent()) {
            for(Person person : eazyClass.get().getPersons()) {
                person.setEazyClass(null);
                personRepository.save(person);
            }
            eazyClassRepository.delete(eazyClass.get());
        }
        return "redirect:/admin/displayClasses";
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(classId);
        modelAndView.addObject("eazyClass",eazyClass.get());
        modelAndView.addObject("person",new Person());
        session.setAttribute("eazyClass",eazyClass.get());
        if(error != null) {
            errorMessage = "Invalid Email entered!!";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("addStudent")
    public String addStudent(String email , Model model , HttpSession session) {
        EazyClass eazyClass =(EazyClass)session.getAttribute("eazyClass");
        Person person = personRepository.findByEmail(email);
        if(person!=null && person.getPersonId() >0) {
            model.addAttribute("person",person);
            person.setEazyClass(eazyClass);
            personRepository.save(person);
            eazyClass.getPersons().add(person);
            eazyClassRepository.save(eazyClass);
            return "redirect:/admin/displayStudents?classId=" + eazyClass.getClassId();
        }
        else{
            return "redirect:/admin/displayStudents?classId=" + eazyClass.getClassId() + "&error=true";
        }
    }

    @RequestMapping("deleteStudent")
    public String deleteStudent(@RequestParam int personId , HttpSession session) {
        Optional<Person> person = personRepository.findById(personId);
        EazyClass eazyClass =(EazyClass)session.getAttribute("eazyClass");
        if(person.isPresent()) {
            Person student =person.get();
            student.setEazyClass(null);
            eazyClass.getPersons().remove(student);
            eazyClassRepository.save(eazyClass);
            personRepository.save(student);
            return "redirect:/admin/displayStudents?classId=" + eazyClass.getClassId();
        }
        else{
            return "redirect:/admin/displayStudents?classId=" + eazyClass.getClassId() + "&error=true";
        }

    }
}
