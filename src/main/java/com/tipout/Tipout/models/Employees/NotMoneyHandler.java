package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.Entity;

@Entity
public class NotMoneyHandler extends Employee {
    private Integer percentOfTipOut;

    public NotMoneyHandler() {
    }

    public NotMoneyHandler(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Integer getPercentOfTipOut() {
        return percentOfTipOut;
    }

    public void setPercentOfTipOut(Integer percentOfTipOut) {
        this.percentOfTipOut = percentOfTipOut;
    }
}
