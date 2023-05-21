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
        model.addAttribute("employer", new Employer());
        if(errors.hasErrors()){
            return "employees/add";
        }
        switch(role){
            case "Bartender":
                Bartender newBartender = new Bartender(newEmployee.getFirstName(),newEmployee.getLastName());
                bartenderRepository.save(newBartender);
                break;
            case "BOH":
                BOH newBOH = new BOH(newEmployee.getFirstName(),newEmployee.getLastName());
                bohRepository.save(newBOH);
                break;
            case "Busser":
                Busser newBusser = new Busser(newEmployee.getFirstName(),newEmployee.getLastName());;
                busserRepository.save(newBusser);
                break;
            case "Server":
                Server newServer = new Server(newEmployee.getFirstName(),newEmployee.getLastName());;
                serverRepository.save(newServer);
                break;
            default:
                model.addAttribute("error", "Something went wrong");
        }

        model.addAttribute("newEmployee", newEmployee.getFirstName());
        model.addAttribute("employee", new Employee());
        return "employees/add";
    }



    @GetMapping("current")
    public String allEmployee(Model model) {
        model.addAttribute("title", "Current Employees");
        model.addAttribute("currentEmployees", employeeRepository.findAll());
        return "employees/current";
    }
}
