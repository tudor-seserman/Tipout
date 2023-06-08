package com.tipout.Tipout.controllers;

import com.tipout.Tipout.models.DTOs.EmployeesDTO;
import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.BOH;
import com.tipout.Tipout.models.Employees.Bartender;
import com.tipout.Tipout.models.Employees.Busser;
import com.tipout.Tipout.models.Employees.Server;
import com.tipout.Tipout.models.Employer;
import com.tipout.Tipout.models.data.EmployeeRepository;
import com.tipout.Tipout.models.data.EmployerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="settings")
public class SettingsController {

    @Autowired
    AuthenticationController authenticationController;

    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployerRepository employerRepository;

    @GetMapping
    public String returnIndex(Model model){
        model.addAttribute("title", "Settings");
        return "settings/index";
    }

    @GetMapping("archive")
    public String allArchivedEmployee(Model model,
                                      HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employer employer = authenticationController.getEmployerFromSession(session);
        model.addAttribute("title", "Archived Employees");
        model.addAttribute("archivedEmployees", employeeRepository.findArhievedEmployees(employer.getId()));
        return "settings/archive";
    }

    @PostMapping("archive")
    public String unArchiveEmployee(@RequestParam Integer employeeToUnArchiveId, Model model,
                                    HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employer employer = authenticationController.getEmployerFromSession(session);
        Optional<Employee> optEmployeeToUnArchive = employeeRepository.findById(employeeToUnArchiveId);
        if (optEmployeeToUnArchive.isEmpty()) {
            model.addAttribute("title", "Archived Employees");
            model.addAttribute("cannotFindEmployee", "cannotFindEmployee");
            model.addAttribute("archivedEmployees", employeeRepository.findArhievedEmployees(employer.getId()));
            return "settings/archive";
        }

        Employee employeeToUnArchive = optEmployeeToUnArchive.get();
        employeeToUnArchive.setDeleted(Boolean.FALSE);
        employeeRepository.save(employeeToUnArchive);

        model.addAttribute("title", "Archived Employees");
        model.addAttribute("archivedEmployees", employeeRepository.findArhievedEmployees(employer.getId()));
        model.addAttribute("employeeToUnArchive", employeeToUnArchive);
        return "settings/archive";
    }

    @GetMapping("tipDistribution")
    public String returnTipDistribution(Model model,
                                        HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employer employer = authenticationController.getEmployerFromSession(session);

        EmployeesDTO employeeDTO = new EmployeesDTO(new ArrayList<>(Arrays.asList(new Bartender(), new BOH(), new Busser(), new Server())));

        model.addAttribute("title", "Tip Distribution");
        model.addAttribute("employeeDTO", employeeDTO);
        return "settings/tipDistribution";
    }

    @PostMapping("tipDistribution")
    public String processTipDistributionForm(Model model, @ModelAttribute EmployeesDTO employeeDTOParam,
                                             HttpServletRequest request) {
        HttpSession session = request.getSession();
        Employer employer = authenticationController.getEmployerFromSession(session);

        System.out.println(employer.getEmployees());
        for(Employee employee: employer.getEmployees()){
            System.out.println(employeeDTOParam.getTipoutInput(employee));
            employee.setPercentOfTipout(employeeDTOParam.getTipoutInput(employee));
            employeeRepository.save(employee);
        }

    employerRepository.save(employer);

        EmployeesDTO employeeDTO = new EmployeesDTO(new ArrayList<>(Arrays.asList(new Bartender(), new BOH(), new Busser(), new Server())));
        model.addAttribute("title", "Tip Distribution");
        model.addAttribute("employeeDTO", employeeDTO);
        return "settings/tipDistribution";
    }

}
