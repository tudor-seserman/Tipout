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
public class TipsCollected {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy ="tips")
    private final List<TipCollector> totalTipsCollected= new ArrayList<>();

    @OneToMany
    private final Map<Employee, Tips> tips = new HashMap<>();

    private double tipsInPool;



    public TipsCollected() {

    }

    public int getId() {
        return id;
    }

    public List<TipCollector> getTotalTipsCollected() {
        return totalTipsCollected;
    }

    public void addTotalTipsCollected(TipCollector tipCollector){
        totalTipsCollected.add(tipCollector);
    }

    public double getTipsInPool() {
        return tipsInPool;
    }

    public void setTipsInPool(double tipsInPool) {
        this.tipsInPool = tipsInPool;
    }

    public Map<Employee, Tips> getTips() {
        return tips;
    }

    public void addTips(Employee employee, Tips tips){
        this.tips.put(employee, tips);
    }

    public void addNonTippedEmployees(Employee employee){
        this.tips.put(employee, new Tips(new BigDecimal(0)));
    }
}
