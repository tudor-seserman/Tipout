package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TippedNotCollector extends Employee {
    private Integer percentOfTipOut;

    public TippedNotCollector() {
    }

    public TippedNotCollector(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Integer getPercentOfTipOut() {
        return percentOfTipOut;
    }

    public void setPercentOfTipOut(Integer percentOfTipOut) {
        this.percentOfTipOut = percentOfTipOut;
    }
}
