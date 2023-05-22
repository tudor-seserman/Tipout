package com.tipout.Tipout.models;

import com.tipout.Tipout.models.Employees.TipCollector;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
