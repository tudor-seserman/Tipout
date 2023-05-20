package com.tipout.Tipout.models.Employees;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TipCollector extends CurrentEmployees {
    private double tipsCollected;
    private Integer percentOfTipOut;

    public TipCollector() {
    }

    public TipCollector(CurrentEmployees role) {
        super(role);
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
