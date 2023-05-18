package com.tipout.Tipout.controllers;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.Bartender;
import com.tipout.Tipout.models.Employer;
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
        model.addAttribute("employee", new Employee());
        model.addAttribute("employer", new Employer());
        return "employees/add";
    }

    @PostMapping("add")
    public String processAddNewEmployee(Model model) {
        model.addAttribute("title", "Add Employee");
        return "employees/add";
    }



    @GetMapping("current")
    public String allEmployee(Model model) {
        model.addAttribute("title", "Current Employee");
        return "employees/current";
    }
}
