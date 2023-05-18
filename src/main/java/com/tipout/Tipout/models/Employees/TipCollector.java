package com.tipout.Tipout.models.Employees;

import com.tipout.Tipout.models.Employee;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class TipCollector extends Employee {
    private double tipsCollected;


    public TipCollector(int id, String firstName, String lastName, double tipsCollected) {
        super(id, firstName, lastName);
        this.tipsCollected = tipsCollected;
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
