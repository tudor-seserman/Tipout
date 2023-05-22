package com.tipout.Tipout.models;

import com.tipout.Tipout.models.Employees.TipCollector;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TipsCollected {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy ="tips")
    private final List<TipCollector> totalTipsCollected= new ArrayList<>();

    @OneToMany
    private final List<Tips> tips = new ArrayList<>();

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

    public List<Tips> getTips() {
        return tips;
    }

    public void addTips(Tips tips){
        this.tips.add(tips);
    }
}
