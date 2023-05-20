package com.tipout.Tipout.models.Employees;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TippedNotCollector extends CurrentEmployees{
    private Integer percentOfTipOut;

    public TippedNotCollector() {
    }

    public TippedNotCollector(CurrentEmployees role) {
    super(role);
    }

    public Integer getPercentOfTipOut() {
        return percentOfTipOut;
    }

    public void setPercentOfTipOut(Integer percentOfTipOut) {
        this.percentOfTipOut = percentOfTipOut;
    }
}
