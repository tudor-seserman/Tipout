package com.tipout.Tipout.controllers;

import com.tipout.Tipout.models.Employees.MoneyHandler;
import com.tipout.Tipout.models.Employees.NotMoneyHandler;
import com.tipout.Tipout.models.Tips;
import com.tipout.Tipout.models.TipsCollected;
import com.tipout.Tipout.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Controller
@RequestMapping(value="calculate")
public class TipoutController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MoneyHandlerRepository moneyHandlerRepository;
    @Autowired
    NotMoneyHandlerRepository notMoneyHandlerRepository;
    @Autowired
    TipsCollectedRepository tipsCollectedRepository;
    @GetMapping
    public String enterTips(Model model){
//        Iterable<Employee> allEmployees = employeeRepository.findAll();
        ArrayList<MoneyHandler> moneyHandlers = (ArrayList<MoneyHandler>) moneyHandlerRepository.findAll();
        ArrayList<NotMoneyHandler> notMoneyHandlers = (ArrayList<NotMoneyHandler>) notMoneyHandlerRepository.findAll();

        model.addAttribute("title","Calculate Tips");
//        model.addAttribute("employeeList", allEmployees);
        model.addAttribute("moneyHandlers", moneyHandlers);
        model.addAttribute("notMoneyHandlers", notMoneyHandlers);

        TipsCollected collectTips = new TipsCollected();

        for(MoneyHandler moneyHandler : moneyHandlers){
            System.out.println(moneyHandler+" moneyHandler");
            collectTips.setMoneyHandlerTipsMap(moneyHandler, new Tips());
        }

        for(NotMoneyHandler notMoneyHandler : notMoneyHandlers){
            System.out.println(notMoneyHandler+" notMoneyHandler");
            collectTips.setNotMoneyHandlerTipsMap(notMoneyHandler, new Tips());
        }

        System.out.println(collectTips.getMoneyHandlerTipsMap());
        System.out.println(collectTips.getNotMoneyHandlerTipsMap());
        model.addAttribute("collectTips", collectTips);
        return "calculate/index";
    }

    @PostMapping("report")
    public String tipReport(Model model,
                            @ModelAttribute TipsCollected tipsCollected){
        model.addAttribute("title","Calculated Tips");
        System.out.println(tipsCollected.getMoneyHandlerTipsMap());
        System.out.println(tipsCollected.getNotMoneyHandlerTipsMap());
        tipsCollected.mergeTables();
        System.out.println(tipsCollected.getMoneyHandlerTipsMap());
        System.out.println(tipsCollected.getNotMoneyHandlerTipsMap());
        System.out.println(tipsCollected.getEmployeeTipsMap());
//        tipsCollectedRepository.save(tipsCollected);

//        Integer id = tipsCollected.getId();
//        List<Employee> employeeTypesInTippool = tipsCollectedRepository.findEmployeeTypesInTippool(id);
//        BigDecimal totalTippool = tipsCollectedRepository.findTotalTippool(id);

//        model.addAttribute("tippool", totalTippool);

        return "calculate/report";
    }



}
