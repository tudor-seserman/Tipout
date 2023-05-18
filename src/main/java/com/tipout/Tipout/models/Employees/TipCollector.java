package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TipCollector extends Employee {
    private double tipsCollected;

    public TipCollector(double tipsCollected) {
    }

    public TipCollector(int id, String name, double percentOfTipOut, double tipsCollected) {
        super(id, name, percentOfTipOut);
        this.tipsCollected = tipsCollected;
    }

    public double getTipsCollected() {
        return tipsCollected;
    }

    public void setTipsCollected(double tipsCollected) {
        this.tipsCollected = tipsCollected;
    }
}
