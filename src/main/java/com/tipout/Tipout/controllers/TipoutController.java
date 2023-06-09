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
/*
This controller handles calculations and the display of those calculations for the app.
It takes employer input and displays how tips are to be distributed.

 */
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

    /*
    This method is in charge of dealing with a simple tip pool. It provides two lists to the view:
    MoneyHandlers - Employees that collect tips tied to a particular Employer
    NonMoneyHandlers - Employees that do not collect tips tied to a particular Employer

    If value is set for a MoneyHandler they are included in the tippool, if no value is set they are not.
    For nonMoneyHandlers if they are selected to be in the tippol they will be included in the distribution.

    Data is collected by a TipsCollected objected and tabulated by a Tipout object
     */
    @GetMapping
    public String enterTips(Model model,
                            @RequestParam(required = false) String error,
                            HttpServletRequest request){
        HttpSession session = request.getSession();
        Employer employer = authenticationController.getEmployerFromSession(session);

        //moneyHandlers tied to a particular Employer
        ArrayList<MoneyHandler> moneyHandlers = (ArrayList<MoneyHandler>) moneyHandlerRepository.findAllByDeletedFalseAndEmployer_Id(employer.getId());
        //nonMoneyHandlers tied to a particular Employer
        ArrayList<NonMoneyHandler> nonMoneyHandlers = (ArrayList<NonMoneyHandler>) nonMoneyHandlerRepository.findAllByDeletedFalseAndEmployer_Id(employer.getId());
        //TipsCollected handles gathering the money and the employees that are in the tippool
        TipsCollected collectTips = new TipsCollected();

        //Employees are paired with a Tip object, which is to be set in the form
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
        //Employees with different roles are fed into the same table,
        // any employees that are not included in the tippool are not included in the new table
        // if no tips are declared an error is thrown
        try {
            tipsCollected.mergeTables();
        }catch (RuntimeException e){
            attributes.addAttribute("error", e.getMessage());
            return "redirect:/calculate";
        }
        Map<Employee,Tips> employeesMap= tipsCollected.getEmployeeTipsMap();

        tipsCollectedRepository.save(tipsCollected);

//        In order to calculate the distribution for the current schema, we need to use the
//        Tipout object which handles the calculation for the current schema we need to pass in three pieces of information:
        long id = tipsCollected.getId();
//        1) The total amount in the tippool
        BigDecimal totalTippool = tipsCollectedRepository.findTotalTippool(id);
//        2) The different types of employees in the tip pool
        List<Integer> employeeTypesInTippool = tipsCollectedRepository.findEmployeeTypesInTippool(id);
//        3) The Employees in the tip pool
        List<Employee> employeesInTipPool = new ArrayList<>(employeesMap.keySet());


        Tipout tipout = new Tipout();
//        We call the calculateTippoolDistribution from the Tipout class which will return a list of Employees with money they are owed
        Map<Employee, Tips> employeeShareofTipoolMap = tipout.calculateTippoolDistribution(employeeTypesInTippool, totalTippool, employeesInTipPool);

        model.addAttribute("title","Calculated Tips");
        model.addAttribute("tippool", totalTippool);
        model.addAttribute("payouts", employeeShareofTipoolMap);

        return "calculate/report";
    }



}
