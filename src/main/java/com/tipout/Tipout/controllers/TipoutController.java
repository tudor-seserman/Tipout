package com.tipout.Tipout.controllers;

import com.tipout.Tipout.models.Employee;
import com.tipout.Tipout.models.Employees.MoneyHandler;
import com.tipout.Tipout.models.Employees.NonMoneyHandler;
import com.tipout.Tipout.models.Tipout;
import com.tipout.Tipout.models.Tips;
import com.tipout.Tipout.models.TipsCollected;
import com.tipout.Tipout.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="calculate")
public class TipoutController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MoneyHandlerRepository moneyHandlerRepository;
    @Autowired
    NonMoneyHandlerRepository nonMoneyHandlerRepository;
    @Autowired
    TipsCollectedRepository tipsCollectedRepository;
    @GetMapping
    public String enterTips(Model model){
        ArrayList<MoneyHandler> moneyHandlers = (ArrayList<MoneyHandler>) moneyHandlerRepository.findAll();
        ArrayList<NonMoneyHandler> nonMoneyHandlers = (ArrayList<NonMoneyHandler>) nonMoneyHandlerRepository.findAll();
        TipsCollected collectTips = new TipsCollected();

        for(MoneyHandler moneyHandler : moneyHandlers){
            collectTips.setMoneyHandlerTipsMap(moneyHandler, new Tips());
        }
        for(NonMoneyHandler nonMoneyHandler : nonMoneyHandlers){
            collectTips.setNonMoneyHandlerTipsMap(nonMoneyHandler, new Tips());
        }

        model.addAttribute("title","Calculate Tips");
        model.addAttribute("moneyHandlers", moneyHandlers);
        model.addAttribute("nonMoneyHandlers", nonMoneyHandlers);
        model.addAttribute("collectTips", collectTips);
        return "calculate/index";
    }

    @PostMapping("report")
    public String tipReport(Model model,
                            @ModelAttribute TipsCollected tipsCollected){
        model.addAttribute("title","Calculated Tips");
        tipsCollected.mergeTables();
        Map<Employee,Tips> employeesMap= tipsCollected.getEmployeeTipsMap();
        tipsCollectedRepository.save(tipsCollected);

        Integer id = tipsCollected.getId();
        BigDecimal totalTippool = tipsCollectedRepository.findTotalTippool(id);
        List<Integer> employeeTypesInTippool = tipsCollectedRepository.findEmployeeTypesInTippool(id);
        List<Employee> employeesInTipPool = new ArrayList<>(employeesMap.keySet());

        Map<Employee, String> employeeShareofTipoolMap = Tipout.calculateTippoolDistribution(employeeTypesInTippool, totalTippool, employeesInTipPool);

        model.addAttribute("tippool", totalTippool);
        model.addAttribute("payouts", employeeShareofTipoolMap);

        return "calculate/report";
    }



}
