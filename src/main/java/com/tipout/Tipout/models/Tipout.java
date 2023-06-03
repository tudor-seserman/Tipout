package com.tipout.Tipout.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.*;
import java.util.stream.IntStream;

@Entity
public class Tipout extends AbstractEntity{
    private static final Map<Employee, String> tipPoolDistribution = new HashMap<>();

    public Tipout() {
    }

    public static Map<Employee, String> calculateTippoolDistribution(List<Integer> tippoolRates, BigDecimal totalTippool, List<Employee> employeesInTippool){
        BigDecimal totalTippoolRates = BigDecimal.valueOf(tippoolRates.stream().mapToInt(i -> i).sum());
        BigDecimal shareOfTippool = totalTippool.divide(totalTippoolRates, 4, RoundingMode.HALF_UP);
        NumberFormat usdCostFormat = NumberFormat.getCurrencyInstance(Locale.US);
        usdCostFormat.setMinimumFractionDigits( 2 );
        usdCostFormat.setMaximumFractionDigits( 2 );
        for(Employee employeeInTippool: employeesInTippool){
            BigDecimal portionOfTippool = shareOfTippool.multiply(employeeInTippool.getPercentOfTipout());
            tipPoolDistribution.put(employeeInTippool, usdCostFormat.format(portionOfTippool));
        }
        return tipPoolDistribution;
    }


//    public Map<Employee, BigDecimal> getTipPoolDistribution() {
//        return tipPoolDistribution;
//    }
//
//    public Map<Employee, BigDecimal> calculateTipPoolDistribution(Map<Employee, Tips> tipsCollected) {
//        BigDecimal tipPool =  BigDecimal.valueOf(0);
//        List<Employee> employeesInTipPool= new ArrayList<>();
//        Map<Employee, Integer> employeeTypeTracker= new HashMap<>();
//        Map<Employee, BigDecimal> employeesWithTipsOwed = new HashMap<>();
//        for(Map.Entry<Employee, Tips> employeeSet: tipsCollected.entrySet()){
//            if(employeeSet.getValue() != null){
//                tipPool = tipPool.add(employeeSet.getValue().getTips());
//                employeesInTipPool.add(employeeSet.getKey());
//            }
//        }
//        return employeesWithTipsOwed;
//    }
}
