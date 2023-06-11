package com.tipout.Tipout.models;

import com.tipout.Tipout.models.Employees.MoneyHandler;
import com.tipout.Tipout.models.Employees.NonMoneyHandler;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

/*
This class is used to aggregate collected tips from employees as well
as the Employees that will receive money from the tip pool.
 */
@Entity
public class TipsCollected extends AbstractEntity{

    @OneToMany(cascade = CascadeType.PERSIST)
    private final Map<Employee, Tips> employeeTipsMap = new LinkedHashMap<>();
    @OneToMany(cascade = CascadeType.PERSIST)
    private final Map<MoneyHandler, Tips> moneyHandlerTipsMap = new LinkedHashMap<>();
    @OneToMany(cascade = CascadeType.PERSIST)
    private final Map<NonMoneyHandler, Tips> nonMoneyHandlerTipsMap = new LinkedHashMap<>();

    public TipsCollected() {}

// All Employees that are in the tip pool with the amount they re contributing
    public Map<Employee, Tips> getEmployeeTipsMap() {
        return employeeTipsMap;
    }

//Employees eligible to be put in tip pool separated by whether they collect tips from customers
    public Map<MoneyHandler, Tips> getMoneyHandlerTipsMap() {
        return moneyHandlerTipsMap;
    }

    public Map<NonMoneyHandler, Tips> getNonMoneyHandlerTipsMap() {return nonMoneyHandlerTipsMap;}


    public void setMoneyHandlerTipsMap(MoneyHandler employee, Tips tips){
        this.moneyHandlerTipsMap.put(employee, tips);
    }

    public void setNonMoneyHandlerTipsMap(NonMoneyHandler employee, Tips tips){
        this.nonMoneyHandlerTipsMap.put(employee, tips);
    }

//    This method filters through the other two maps, excluding anyone who does not have a set value.
//    If no money is added to the tippool, an error is thrown.
    public void mergeTables(){
        this.moneyHandlerTipsMap.forEach((k,v)->{ if(v.getTips() !=null)this.employeeTipsMap.put(k,v);});
//  If there are no Employees that collect tips an error is thrown
        if(employeeTipsMap.isEmpty()){throw new RuntimeException("No tips have been declared.");}
//  If no money is collected an error is thrown.
        BigDecimal currentTipPool = new BigDecimal(0);
        this.employeeTipsMap.forEach((k,v)->{currentTipPool.add(v.getTips());});
        if (currentTipPool.equals(new BigDecimal(0))){throw new RuntimeException("No tips have been declared.");}

        this.nonMoneyHandlerTipsMap.forEach((k,v)->{if(v.getTips() != null)this.employeeTipsMap.put(k,v);});
    }
}
