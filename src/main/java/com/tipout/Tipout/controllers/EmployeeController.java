package com.tipout.Tipout.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="employees")
public class EmployeeController {

    @GetMapping("add")
    public String addNewEmployee(Model model) {
        model.addAttribute("title", "Add Employee");
        return "employees/add";
    }

    @PostMapping("add")
    public String processaddNewEmployee(Model model) {
        model.addAttribute("title", "Add Employee");
        return "redirect:/";
    }



    @GetMapping("current")
    public String allEmployee(Model model) {
        model.addAttribute("title", "Current Employee");
        return "employees/current";
    }
}
