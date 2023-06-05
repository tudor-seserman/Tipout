package com.tipout.Tipout.models;

import com.tipout.Tipout.models.Employees.MoneyHandler;
import com.tipout.Tipout.models.Employees.NonMoneyHandler;

import javax.persistence.*;
import java.util.*;

/*
This class is used to aggregate collected tips from employees as well
as the Employees that will receive money from the tip pool.
 */
@Entity
public class TipsCollected extends AbstractEntity{
//    I don't think I need this field
//    @OneToMany(mappedBy ="tips")
//    private final List<MoneyHandler> totalTipsCollected= new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private final Map<Employee, Tips> employeeTipsMap = new LinkedHashMap<>();
    @OneToMany(cascade = CascadeType.ALL)
    private final Map<MoneyHandler, Tips> moneyHandlerTipsMap = new LinkedHashMap<>();
    @OneToMany(cascade = CascadeType.ALL)
    private final Map<NonMoneyHandler, Tips> nonMoneyHandlerTipsMap = new LinkedHashMap<>();

    public TipsCollected() {}


    public Map<Employee, Tips> getEmployeeTipsMap() {
        return employeeTipsMap;
    }

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

    public void mergeTables(){
        this.moneyHandlerTipsMap.forEach((k,v)->{ if(v.getTips() !=null)this.employeeTipsMap.put(k,v);});
        if(employeeTipsMap.isEmpty()){throw new RuntimeException("No tips have been declared.");}
        this.nonMoneyHandlerTipsMap.forEach((k,v)->{if(v.getTips() != null)this.employeeTipsMap.put(k,v);});
    }
}
