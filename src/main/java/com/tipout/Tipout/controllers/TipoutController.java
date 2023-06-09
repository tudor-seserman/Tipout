package com.tipout.Tipout.controllers;

import com.tipout.Tipout.models.*;
import com.tipout.Tipout.models.Employees.MoneyHandler;
import com.tipout.Tipout.models.Employees.NonMoneyHandler;
import com.tipout.Tipout.models.data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="calculate")
public class TipoutController {
    @Autowired
    AuthenticationController authenticationController;
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    MoneyHandlerRepository moneyHandlerRepository;
    @Autowired
    NonMoneyHandlerRepository nonMoneyHandlerRepository;
    @Autowired
    TipsCollectedRepository tipsCollectedRepository;
    @GetMapping
    public String enterTips(Model model,
                            @RequestParam(required = false) String error,
                            HttpServletRequest request){
        HttpSession session = request.getSession();
        Employer employer = authenticationController.getEmployerFromSession(session);

        ArrayList<MoneyHandler> moneyHandlers = (ArrayList<MoneyHandler>) moneyHandlerRepository.findAllByDeletedFalseAndEmployer_Id(employer.getId());
        ArrayList<NonMoneyHandler> nonMoneyHandlers = (ArrayList<NonMoneyHandler>) nonMoneyHandlerRepository.findAllByDeletedFalseAndEmployer_Id(employer.getId());
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
        model.addAttribute("error", error);
        return "calculate/index";
    }

    @PostMapping("report")
    public String tipReport(Model model,
                            @ModelAttribute TipsCollected tipsCollected,
                            RedirectAttributes attributes){
        try {
            tipsCollected.mergeTables();
        }catch (RuntimeException e){
            attributes.addAttribute("error", e.getMessage());
            return "redirect:/calculate";
        }
        Map<Employee,Tips> employeesMap= tipsCollected.getEmployeeTipsMap();

        tipsCollectedRepository.save(tipsCollected);

        long id = tipsCollected.getId();
        BigDecimal totalTippool = tipsCollectedRepository.findTotalTippool(id);
        List<Integer> employeeTypesInTippool = tipsCollectedRepository.findEmployeeTypesInTippool(id);
        List<Employee> employeesInTipPool = new ArrayList<>(employeesMap.keySet());
        Tipout tipout = new Tipout();
        Map<Employee, Tips> employeeShareofTipoolMap = tipout.calculateTippoolDistribution(employeeTypesInTippool, totalTippool, employeesInTipPool);

        model.addAttribute("title","Calculated Tips");
        model.addAttribute("tippool", totalTippool);
        model.addAttribute("payouts", employeeShareofTipoolMap);

        return "calculate/report";
    }



}
