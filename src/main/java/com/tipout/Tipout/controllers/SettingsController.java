package com.tipout.Tipout.controllers;

import com.tipout.Tipout.models.DTOs.EmployeesDTO;
import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.Bartender;
import com.tipout.Tipout.models.Employer;
import com.tipout.Tipout.models.data.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="settings")
public class SettingsController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public String returnIndex(Model model){
        model.addAttribute("title", "Settings");
        return "settings/index";
    }

    @GetMapping("archive")
    public String allArchivedEmployee(Model model) {
        model.addAttribute("title", "Archived Employees");
        model.addAttribute("archivedEmployees", employeeRepository.findArhievedEmployees());
        return "settings/archive";
    }

    @PostMapping("archive")
    public String unArchiveEmployee(@RequestParam Integer employeeToUnArchiveId, Model model) {
        Optional<Employee> optEmployeeToUnArchive = employeeRepository.findById(employeeToUnArchiveId);
        if (optEmployeeToUnArchive.isEmpty()) {
            model.addAttribute("title", "Archived Employees");
            model.addAttribute("cannotFindEmployee", "cannotFindEmployee");
            model.addAttribute("archivedEmployees", employeeRepository.findArhievedEmployees());
            return "settings/archive";
        }

        Employee employeeToUnArchive = optEmployeeToUnArchive.get();
        employeeToUnArchive.setDeleted(Boolean.FALSE);
        employeeRepository.save(employeeToUnArchive);

        model.addAttribute("title", "Archived Employees");
        model.addAttribute("archivedEmployees", employeeRepository.findArhievedEmployees());
        model.addAttribute("employeeToUnArchive", employeeToUnArchive);
        return "settings/archive";
    }

    @GetMapping("tipDistribution")
    public String returnTipDistribution(Model model){

//        This will be need to be refactored to handle different Employers
        EmployeesDTO employeeTypesList = new EmployeesDTO(new Employer().getEmployees());

        model.addAttribute("title", "Tip Distribution");
        model.addAttribute("employer", employeeTypesList);
        return "settings/tipDistribution";
    }

    @PostMapping("tipDistribution")
    public String processTipDistributionForm(Model model){
        return "settings/tipDistribution";
    }

}
