package com.tipout.Tipout.controllers;

import com.tipout.Tipout.models.DTOs.EmployeesDTO;
import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.*;
import com.tipout.Tipout.models.Employer;
import com.tipout.Tipout.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.Optional;

@Controller
@RequestMapping(value="employees")
public class EmployeeController {
    @Autowired
    AuthenticationController authenticationController;
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

        EmployeesDTO employeesDTO = new EmployeesDTO(Arrays.asList(new Bartender(), new BOH(), new Busser(), new Server()));

        model.addAttribute("title", "Add Employee");
        model.addAttribute("employee", new Employee());
        model.addAttribute("employeeTypes", employeesDTO);
        return "employees/add";
    }

    @PostMapping("add")
    public String processAddNewEmployee(@RequestParam String role,
                                        Model model,
                                        @ModelAttribute @Valid Employee newEmployee,
                                        Errors errors,
                                        HttpServletRequest request
    ) {
        HttpSession session = request.getSession();
        Employer employer = authenticationController.getEmployerFromSession(session);


        if (errors.hasErrors()) {
            return "employees/add";
        }
        switch (role) {
            case "Bartender":
                Bartender newBartender = new Bartender(newEmployee.getFirstName(), newEmployee.getLastName(), employer);
                bartenderRepository.save(newBartender);
                break;
            case "BOH":
                BOH newBOH = new BOH(newEmployee.getFirstName(), newEmployee.getLastName(), employer);
                bohRepository.save(newBOH);
                break;
            case "Busser":
                Busser newBusser = new Busser(newEmployee.getFirstName(), newEmployee.getLastName(), employer);
                ;
                busserRepository.save(newBusser);
                break;
            case "Server":
                Server newServer = new Server(newEmployee.getFirstName(), newEmployee.getLastName(), employer);
                ;
                serverRepository.save(newServer);
                break;
            default:
                model.addAttribute("error", "Something went wrong");
        }
//Eventually Employers will ba able to choose the employee types they want to have in their system
        EmployeesDTO employeesDTO = new EmployeesDTO(Arrays.asList(new Bartender(), new BOH(), new Busser(), new Server()));

        model.addAttribute("title", "Add Employee");
        model.addAttribute("employeeTypes", employeesDTO);
        model.addAttribute("newEmployee", newEmployee.getFirstName());
        model.addAttribute("employee", new Employee());
        return "employees/add";
    }


    @GetMapping("current")
    public String allEmployee(Model model,
                                HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employer employer = authenticationController.getEmployerFromSession(session);

        model.addAttribute("title", "Current Employees");
        model.addAttribute("currentEmployees", employeeRepository.findCurrentEmployees(employer.getId()));
        return "employees/current";
    }

    @GetMapping("edit/{employeeToEditId}")
    public String editEmployeeForm(@PathVariable Long employeeToEditId, Model model,
                                   HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employer employer = authenticationController.getEmployerFromSession(session);

        Optional<Employee> optEmployeeToEdit = employeeRepository.findById(employeeToEditId);
        if (optEmployeeToEdit.isEmpty()) {
            model.addAttribute("title", "Current Employees");
            model.addAttribute("currentEmployees", employeeRepository.findCurrentEmployees(employer.getId()));
            model.addAttribute("cannotFindEmployee","cannotFindEmployee");
            return "employees/current";
        }
        Employee employeeToEdit = optEmployeeToEdit.get();

        model.addAttribute("title",
                "Edit "+employeeToEdit);
        model.addAttribute("employeeToEdit", employeeToEdit);
        model.addAttribute("employeeToEditId", employeeToEditId);
        return "employees/edit";
    }

    @PostMapping("edit/{employeeToEditId}")
    public String editEmployeeProcessing(@PathVariable Long employeeToEditId,
                                         Model model,
                                         String firstName,
                                         String lastName,
                                         @RequestParam Boolean archive ,
                                         HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employer employer = authenticationController.getEmployerFromSession(session);
        Optional<Employee> optEmployeeToEdit = employeeRepository.findById(employeeToEditId);
        if (optEmployeeToEdit.isEmpty()) {
            model.addAttribute("title", "Current Employees");
            model.addAttribute("currentEmployees", employeeRepository.findCurrentEmployees(employer.getId()));
            model.addAttribute("cannotFindEmployee", "cannotFindEmployee");
            return "redirect:/employees/current";
        }

        Employee employeeToEdit = optEmployeeToEdit.get();

        if(archive) {
            employeeToEdit.setDeleted(Boolean.TRUE);
            model.addAttribute("archive","archive");
        }else{
            employeeToEdit.setFirstName(firstName);
            employeeToEdit.setLastName(lastName);
            model.addAttribute("success","success");
        }

        employeeRepository.save(employeeToEdit);


        model.addAttribute("title",
                "Edit "+employeeToEdit);
        model.addAttribute("employeeToEdit", employeeToEdit);
        model.addAttribute("employeeToEditId", employeeToEditId);
        return "employees/edit";
    }

    @PostMapping("delete/{employeeToDeleteId}")
    public String deleteEmployeeProcessing(@PathVariable Long employeeToDeleteId, Model model, Boolean confirmation) {
        Optional<Employee> optEmployeeToDelete = employeeRepository.findById(employeeToDeleteId);
        if (optEmployeeToDelete.isEmpty()) {
            model.addAttribute("title", "Current Employees");
            model.addAttribute("currentEmployees", employeeRepository.findAll());
            model.addAttribute("cannotFindEmployee", "cannotFindEmployee");
            return "redirect:/employees/current";
        }

        Employee employeeToDelete = optEmployeeToDelete.get();

        if(confirmation){
            employeeRepository.completelyDeleteEmployeeTipRecord(employeeToDeleteId);
            if(employeeToDelete instanceof MoneyHandler){employeeRepository.completelyDeleteMoneyhandlerTipRecord(employeeToDeleteId);}
            if(employeeToDelete instanceof NonMoneyHandler){employeeRepository.completelyDeleteNonMoneyhandlerTipRecord(employeeToDelete.getId());}
            employeeRepository.deleteById(employeeToDeleteId);

            model.addAttribute("title", "Current Employees");
            model.addAttribute("currentEmployees", employeeRepository.findAll());
            model.addAttribute("delete","delete");
            return "redirect:/employees/current";
        }

        model.addAttribute("title", "Delete Employee");
        model.addAttribute("employeeToDelete", employeeToDelete);
        model.addAttribute("employeeToDeletedId", employeeToDeleteId);
        return "employees/delete";
    }
}