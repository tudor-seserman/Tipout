package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TipCollector extends Employee {
    private double tipsCollected;
    private Integer percentOfTipOut;

    public TipCollector() {
    }

    public TipCollector(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public double getTipsCollected() {
        return tipsCollected;
    }

    public void setTipsCollected(double tipsCollected) {
        this.tipsCollected = tipsCollected;
    }

    public Integer getPercentOfTipOut() {
        return percentOfTipOut;
    }

    public void setPercentOfTipOut(Integer percentOfTipOut) {
        this.percentOfTipOut = percentOfTipOut;
    }
}
