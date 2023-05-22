package com.tipout.Tipout.controllers;

import com.tipout.Tipout.models.Employees.Bartender;
import com.tipout.Tipout.models.Employees.TipCollector;
import com.tipout.Tipout.models.Employees.TippedNotCollector;
import com.tipout.Tipout.models.Tips;
import com.tipout.Tipout.models.TipsCollected;
import com.tipout.Tipout.models.data.BartenderRepository;
import com.tipout.Tipout.models.data.EmployeeRepository;
import com.tipout.Tipout.models.data.TipCollectorRepository;
import com.tipout.Tipout.models.data.TippedNotCollectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        TipsCollected collectTips = new TipsCollected();
//        model.addAttribute("tipCollected", new TipsCollected());

        model.addAttribute("tipCollectorsList", tipCollectorRepository.findAll());
        model.addAttribute("notTipCollectorsList",tippedNotCollectorRepository.findAll());

        for(TipCollector collector : tipCollectorRepository.findAll()){
            collectTips.addTips(new Tips());
        }
        model.addAttribute("tipCollector", collectTips);
        return "calculate/index";
    }

    @PostMapping("report")
    public String tipReport(Model model,
                            @ModelAttribute TipsCollected tipsCollected){
        model.addAttribute("title","Calculated Tips");
        System.out.println(tipsCollected.getTotalTipsCollected());
        return "calculate/report";
    }



}
