package com.tipout.Tipout.controllers;

import com.tipout.Tipout.models.Employees.Bartender;
import com.tipout.Tipout.models.data.BartenderRepository;
import com.tipout.Tipout.models.data.EmployeeRepository;
import com.tipout.Tipout.models.data.TipCollectorRepository;
import com.tipout.Tipout.models.data.TippedNotCollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="calculate")
public class TipoutController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    TipCollectorRepository tipCollectorRepository;
    @Autowired
    TippedNotCollectorRepository tippedNotCollectorRepository;
    @GetMapping
    public String enterTips(Model model){
        model.addAttribute("title","Calculate Tips");
        model.addAttribute("tipCollectors", tipCollectorRepository.findAll());
        model.addAttribute("notTipCollectors",tippedNotCollectorRepository.findAll());
        return "calculate/index";
    }



}
