package com.tipout.Tipout.models;

import com.tipout.Tipout.models.Employees.TipCollector;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/*
This class is used to aggregate collected tips from employees as well
as the Employees that will receive money from the tip pool.
 */
@Entity
public class TipsCollected extends AbstractEntity{
//    I don't think I need this field
    @OneToMany(mappedBy ="tips")
    private final List<TipCollector> totalTipsCollected= new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    private final Map<Employee, Tips> employeeTipsMap = new HashMap<>();

    public TipsCollected() {}

    public List<TipCollector> getTotalTipsCollected() {
        return totalTipsCollected;
    }

    public void addTotalTipsCollected(TipCollector tipCollector){
        totalTipsCollected.add(tipCollector);
    }

    public Map<Employee, Tips> getEmployeeTipsMap() {
        return employeeTipsMap;
    }

    public void addEmployeeTipsMap(Employee employee, Tips tips){
        this.employeeTipsMap.put(employee, tips);
    }

    public void addNonTippedEmployees(Employee employee){
        this.employeeTipsMap.put(employee, new Tips(new BigDecimal(0)));
    }
}
