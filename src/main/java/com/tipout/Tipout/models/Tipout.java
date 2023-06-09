package com.tipout.Tipout.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;
@Entity
public class Tipout extends AbstractEntity{
    @OneToMany(cascade = CascadeType.ALL)
    private final Map<Employee, Tips> tipPoolDistribution = new HashMap<>();

    public Tipout() {
    }
/*
This is the first tipout schema I have instantiated.
Employee rates are best understood as the fractional shares of tippool.
The amount of

First all the employee rates are in the tip pool are added together to determine the
the pool of money is divided by role according to Tip Rate assigned to that role
The money for each role is divided by members of that role.
 */
    public Map<Employee, Tips> calculateTippoolDistribution(List<Integer> tippoolRates, BigDecimal totalTippool, List<Employee> employeesInTippool){

//        This method receives three pieces of information from the TipoutController, total tips, rates in tip pool, employees that need to be tipped out

//        We first add up all the different tip rates in the tip pool
        BigDecimal totalTippoolRates = BigDecimal.valueOf(tippoolRates.stream().mapToInt(i -> i).sum());
//        We divide the total money by all the rates of the employees in the tip pool
//        shareOfTippool is the monatery value of each employee share of the tippool
        BigDecimal shareOfTippool = totalTippool.divide(totalTippoolRates, 4, RoundingMode.HALF_UP);


        for(Employee employeeInTippool: employeesInTippool){
            BigDecimal portionOfTippool = shareOfTippool.multiply(new BigDecimal(employeeInTippool.getPercentOfTipout()));
            tipPoolDistribution.put(employeeInTippool, new Tips(portionOfTippool));
        }
        return tipPoolDistribution;
    }

}
