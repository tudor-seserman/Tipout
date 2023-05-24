package com.tipout.Tipout.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.*;

@Entity
public class Tipout extends AbstractEntity{
//    private final Map<Employee, BigDecimal> tipPoolDistribution = new HashMap<>();

    public Tipout() {
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
