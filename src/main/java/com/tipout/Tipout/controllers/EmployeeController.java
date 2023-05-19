package com.tipout.Tipout.controllers;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.BOH;
import com.tipout.Tipout.models.Employees.Bartender;
import com.tipout.Tipout.models.Employees.Busser;
import com.tipout.Tipout.models.Employees.Server;
import com.tipout.Tipout.models.Employer;
import com.tipout.Tipout.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value="employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    BartenderRepository bartenderRepository;
    @Autowired
    BOHRepository bohRepository;
    @Autowired
    BusserRepository busserRepository;
    @Autowired
    ServerRepository serverRepository;



    @GetMapping("add")
    public String addNewEmployee(Model model) {
        model.addAttribute("title", "Add Employee");
        model.addAttribute("employee", new Employee());
        model.addAttribute("employer", new Employer());
        return "employees/add";
    }

    @PostMapping("add")
    public String processAddNewEmployee(@RequestParam String role, Model model, @ModelAttribute @Valid Employee newEmployee, Errors errors) {
        model.addAttribute("title", "Add Employee");
        model.addAttribute("employee", new Employee());
        model.addAttribute("employer", new Employer());
        if(errors.hasErrors()){
            return "employees/add";
        }
        switch(role){
            case "Bartender":
                Bartender newBartender = (Bartender) newEmployee;
                bartenderRepository.save(newBartender);
                break;
            case "BOH":
                BOH newBOH = (BOH) newEmployee;
                bohRepository.save(newBOH);
                break;
            case "Busser":
                Busser newBusser = (Busser) newEmployee;
                busserRepository.save(newBusser);
                break;
            case "Server":
                Server newServer = (Server) newEmployee;
                serverRepository.save(newServer);
                break;
            default:
                model.addAttribute("newEmployee", "Something went wrong");
        }

        model.addAttribute("newEmployee", newEmployee.getFirstName());
        return "employees/add";
    }



    @GetMapping("current")
    public String allEmployee(Model model) {
        model.addAttribute("title", "Current Employee");
        return "employees/current";
    }
}
