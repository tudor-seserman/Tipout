package com.tipout.Tipout.models;

import com.tipout.Tipout.models.Employees.MoneyHandler;
import com.tipout.Tipout.models.Employees.NotMoneyHandler;

import javax.persistence.*;
import java.util.*;

/*
This class is used to aggregate collected tips from employees as well
as the Employees that will receive money from the tip pool.
 */
@Entity
public class TipsCollected extends AbstractEntity{
//    I don't think I need this field
    @OneToMany(mappedBy ="tips")
    private final List<MoneyHandler> totalTipsCollected= new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    private final Map<Employee, Tips> employeeTipsMap = new LinkedHashMap<>();
    @OneToMany(cascade = CascadeType.PERSIST)
    private final Map<MoneyHandler, Tips> moneyHandlerTipsMap = new LinkedHashMap<>();
    @OneToMany(cascade = CascadeType.PERSIST)
    private final Map<NotMoneyHandler, Tips> notMoneyHandlerTipsMap = new LinkedHashMap<>();

    public TipsCollected() {}

    public List<MoneyHandler> getTotalTipsCollected() {
        return totalTipsCollected;
    }

    public void addTotalTipsCollected(MoneyHandler moneyHandler){
        totalTipsCollected.add(moneyHandler);
    }

    public Map<Employee, Tips> getEmployeeTipsMap() {
        return employeeTipsMap;
    }

    public Map<MoneyHandler, Tips> getMoneyHandlerTipsMap() {
        return moneyHandlerTipsMap;
    }

    public Map<NotMoneyHandler, Tips> getNotMoneyHandlerTipsMap() {
        return notMoneyHandlerTipsMap;
    }

    public void addMoneyHandlerTipsMap(MoneyHandler employee, Tips tips){
        this.moneyHandlerTipsMap.put(employee, tips);
    }

    public void addNotMoneyHandlerTipsMap(NotMoneyHandler employee){
        this.notMoneyHandlerTipsMap.put(employee, null);
    }

    public void mergeTables(){
        this.employeeTipsMap.putAll(this.notMoneyHandlerTipsMap);
        this.employeeTipsMap.putAll(this.moneyHandlerTipsMap);
    }
}
