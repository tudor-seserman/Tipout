package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TipCollector extends Employee {
    private double tipsCollected;


    public TipCollector(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }

    public TipCollector() {

    }

    public double getTipsCollected() {
        return tipsCollected;
    }

    public void setTipsCollected(double tipsCollected) {
        this.tipsCollected = tipsCollected;
    }
}
