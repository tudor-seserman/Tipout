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

    public Map<Employee, Tips> calculateTippoolDistribution(List<Integer> tippoolRates, BigDecimal totalTippool, List<Employee> employeesInTippool){
        System.out.println(tippoolRates);
        System.out.println(totalTippool);
        System.out.println(employeesInTippool);
        BigDecimal totalTippoolRates = BigDecimal.valueOf(tippoolRates.stream().mapToInt(i -> i).sum());
        System.out.println(totalTippoolRates);
        BigDecimal shareOfTippool = totalTippool.divide(totalTippoolRates, 4, RoundingMode.HALF_UP);
        System.out.println(shareOfTippool);

        for(Employee employeeInTippool: employeesInTippool){
            BigDecimal portionOfTippool = shareOfTippool.multiply(employeeInTippool.getPercentOfTipout());
            tipPoolDistribution.put(employeeInTippool, new Tips(portionOfTippool));
        }
        return tipPoolDistribution;
    }

}
